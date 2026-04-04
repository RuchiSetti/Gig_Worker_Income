package com.guidewire.gigsuraksha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.Zone;

import com.guidewire.gigsuraksha.repository.zonerepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class zoneserviceimpl implements zoneservice {

    @Autowired
    private zonerepository repository;

    @Override
    public BigDecimal getZoneRiskFactor(UUID zoneId) {
        return repository.findById(zoneId)
                .map(Zone::getRiskFactorR)
                .orElse(BigDecimal.ZERO); // default if zone not found
    }

    @Override
    public void updateRiskScore(UUID zoneId, double newRiskScore) {
        repository.findById(zoneId).ifPresent(zone -> {
            zone.setRiskFactorR(BigDecimal.valueOf(newRiskScore));
            zone.setLastRiskUpdated(LocalDate.now());
            repository.save(zone);
        });
    }

    @Override
    public Zone getActivePartners(UUID zoneId) {
        // You probably want to fetch zone details; returning the entity
        return repository.findById(zoneId).orElse(null);
    }
}