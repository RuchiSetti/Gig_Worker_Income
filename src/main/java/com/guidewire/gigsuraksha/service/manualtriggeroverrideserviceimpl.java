package com.guidewire.gigsuraksha.service;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.ManualTriggerOverride;
import com.guidewire.gigsuraksha.repository.manualtriggeroverriderepository;

import java.time.LocalDateTime;
	import java.util.Optional;
	import java.util.UUID;

	@Service
	public class manualtriggeroverrideserviceimpl implements manualtriggeroverrideservice {


	    @Autowired
	    private manualtriggeroverriderepository repository;

	    @Override
	    public void createOverride(UUID zoneId, String reason) {

	        ManualTriggerOverride override = new ManualTriggerOverride();

	        // keep id auto (no manual random)
	        override.setZoneId(zoneId);
	        override.setReason(reason);
	        override.setDescription("Manual override created by admin");

	        // ⚡️ ADMIN SIDE INPUT (instead of random)
	        // assume frontend sends adminId + linkedEventId
	        override.setAdminId(zoneId); // TEMP: replace with real adminId from frontend
	        override.setLinkedEventId(zoneId); // TEMP: replace with real eventId

	        override.setActiveFrom(LocalDateTime.now());
	        override.setActiveUntil(LocalDateTime.now().plusDays(1));

	        repository.save(override);
	    }

	    @Override
	    public void expireOverride(UUID overrideId) {

	        Optional<ManualTriggerOverride> optional = repository.findById(overrideId);

	        if (optional.isPresent()) {
	            ManualTriggerOverride override = optional.get();

	            override.setActiveUntil(LocalDateTime.now());

	            repository.save(override);
	        }
	    }

	    @Override
	    public ManualTriggerOverride getActiveOverrides(UUID overrideId) {

	        Optional<ManualTriggerOverride> optional = repository.findById(overrideId);

	        return optional.orElse(null);
	    }
	}
