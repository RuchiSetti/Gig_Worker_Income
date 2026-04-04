package com.guidewire.gigsuraksha.service;



	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.Zone;
import com.guidewire.gigsuraksha.repository.zonerepository;

import java.time.LocalDate;
	import java.util.Optional;
	import java.util.UUID;

	@Service
	public class zoneserviceimpl implements zoneservice {

		@Autowired
	    private zonerepository repository;

	    @Override
	    public void getZoneRiskFactor(UUID zoneId) {

	        Optional<Zone> optional = repository.findById(zoneId);

	        if (optional.isPresent()) {
	            optional.get().getRiskFactorR();
	        }
	    }

	    @Override
	    public void updateRiskScore(UUID zoneId) {

	        Optional<Zone> optional = repository.findById(zoneId);

	        if (optional.isPresent()) {
	            Zone zone = optional.get();

	            zone.setRiskFactorR(0.7);
	            zone.setLastRiskUpdated(LocalDate.now());

	            repository.save(zone);
	        }
	    }

	    @Override
	    public void getActivePartners(UUID zoneId) {

	        Optional<Zone> optional = repository.findById(zoneId);

	        if (optional.isPresent()) {
	            optional.get();
	        }
	    }
	}