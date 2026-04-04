package com.guidewire.gigsuraksha.service;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.RiskSnapShot;
import com.guidewire.gigsuraksha.repository.claimrepository;
import com.guidewire.gigsuraksha.repository.deliverypartnerrepository;
import com.guidewire.gigsuraksha.repository.risksnapshotrepository;

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
	        snapshot.setZoneId(zoneId); // ✅ use incoming zoneId
	        snapshot.setWeekStart(LocalDate.now());

	        // 🔥 REAL DATA (filtered by zone)
	        snapshot.setActivePartners(
	            (int)deliveryPartnerRepo.findAll().stream()
	                .filter(p -> zoneId.equals(p.getZoneId()))
	                .count()
	        );

	        snapshot.setTotalExposure(
	        	    claimRepo.findAll().stream()
	        	        .filter(c -> {
	        	            return deliveryPartnerRepo.findById(c.getPartnerId())
	        	                    .map(p -> zoneId.equals(p.getZoneId()))
	        	                    .orElse(false);
	        	        })
	        	        .mapToDouble(c -> c.getFinalPayout() == null ? 0.0 : c.getFinalPayout())
	        	        .sum()
	        	);

	        snapshot.setCreatedAt(LocalDateTime.now());

	        repository.save(snapshot);
	    }
	    @Override
	    public void getZoneExposure(UUID zoneId) {

	        // You can later optimize using custom query
	        repository.findAll().stream()
	                .filter(s -> s.getZoneId().equals(zoneId))
	                .forEach(s -> s.getTotalExposure());
	    }

	    @Override
	    public void flagHighRiskZones(UUID snapshotId) {

	        Optional<RiskSnapShot> optional = repository.findById(snapshotId);

	        if (optional.isPresent()) {
	        	RiskSnapShot snapshot = optional.get();

	            if (snapshot.getComputedCrf() != null && snapshot.getComputedCrf() > 0.7) {
	                snapshot.setSeasonalRiskIndex(0.9); // high risk zone
	            }

	            repository.save(snapshot);
	        }
	    }
	}
