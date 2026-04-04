package com.guidewire.gigsuraksha.service;

import com.guidewire.gigsuraksha.entity.RiskSnapShot;

import com.guidewire.gigsuraksha.repository.claimrepository;
import com.guidewire.gigsuraksha.repository.deliverypartnerrepository;
import com.guidewire.gigsuraksha.repository.risksnapshotrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class risksnapshotserviceimpl implements risksnapshotservice {

    @Autowired
    private risksnapshotrepository repository;

    @Autowired
    private claimrepository claimRepo;

    @Autowired
    private deliverypartnerrepository deliveryPartnerRepo;

    @Override
    public void generateWeeklySnapshot(UUID zoneId) {

        RiskSnapShot snapshot = new RiskSnapShot();

        snapshot.setSnapshotId(UUID.randomUUID());
        snapshot.setZoneId(zoneId);
        snapshot.setWeekStart(LocalDate.now());

        // Count active partners in this zone
        int activePartners = (int) deliveryPartnerRepo.findAll().stream()
                .filter(p -> zoneId.equals(p.getZoneId()))
                .count();
        snapshot.setActivePartners(activePartners);

        // Calculate total exposure for this zone
        

        BigDecimal totalExposure = claimRepo.findAll().stream()
                .filter(c -> deliveryPartnerRepo.findById(c.getPartnerId())
                        .map(p -> zoneId.equals(p.getZoneId()))
                        .orElse(false))
                .map(c -> c.getFinalPayout() == null ? BigDecimal.ZERO : c.getFinalPayout())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        snapshot.setTotalExposure(totalExposure);
        snapshot.setCreatedAt(LocalDateTime.now());

        repository.save(snapshot);
    }

    @Override
    public BigDecimal getZoneExposure(UUID zoneId) {
        // Sum totalExposure of all snapshots for this zone
        return repository.findAll().stream()
                .filter(s -> zoneId.equals(s.getZoneId()))
                .map(RiskSnapShot::getTotalExposure)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void flagHighRiskZones(UUID snapshotId) {

        Optional<RiskSnapShot> optional = repository.findById(snapshotId);

        if (optional.isPresent()) {
            RiskSnapShot snapshot = optional.get();

            // Proper BigDecimal comparison
            if (snapshot.getComputedCrf() != null &&
                snapshot.getComputedCrf().compareTo(BigDecimal.valueOf(0.7)) > 0) {

                snapshot.setSeasonalRiskIndex(BigDecimal.valueOf(0.9)); // high risk zone
            }

            repository.save(snapshot);
        }
    }
}