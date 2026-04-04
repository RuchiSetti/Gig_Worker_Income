package com.guidewire.gigsuraksha.service;



	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.Claim;
import com.guidewire.gigsuraksha.entity.DisruptionEvent;
import com.guidewire.gigsuraksha.repository.claimrepository;
import com.guidewire.gigsuraksha.repository.disruptioneventrepository;

import java.time.LocalDateTime;
	import java.util.Optional;
	import java.util.UUID;

	@Service
	public class disruptioneventserviceimpl implements disruptioneventservice {
		@Autowired
		private claimrepository claimRepo;
		  @Autowired
		    private disruptioneventrepository repository;

		  @Override
		  public void detectTrigger(UUID zoneId) {

		      DisruptionEvent event = new DisruptionEvent();

		      event.setZoneId(zoneId);
		      event.setEventType("heavy_rain");
		      event.setSeverity("high");
		      event.setEventStart(LocalDateTime.now());

		      repository.save(event);

		      // 🔥 AUTO CREATE CLAIM
		      Claim claim = new Claim();

		      claim.setTriggerEventId(event.getEventId());
		      claim.setStatus("pending");

		      claimRepo.save(claim);
		  }
		    @Override
		    public void broadcastToPartners(UUID eventId) {

		        Optional<DisruptionEvent> optional = repository.findById(eventId);

		        if (optional.isPresent()) {
		            DisruptionEvent event = optional.get();

		            // simulate broadcast (no logic change)
		            event.setAffectedPartnersCount(event.getAffectedPartnersCount());

		            repository.save(event);
		        }
		    }

		    @Override
		    public void computeCRF(UUID eventId) {

		        Optional<DisruptionEvent> optional = repository.findById(eventId);

		        if (optional.isPresent()) {
		            DisruptionEvent event = optional.get();

		            event.setCrfValue(0.7);

		            repository.save(event);
		        }
		    }

		    @Override
		    public void closeEvent(UUID eventId) {

		        Optional<DisruptionEvent> optional = repository.findById(eventId);

		        if (optional.isPresent()) {
		            DisruptionEvent event = optional.get();

		            event.setEventEnd(LocalDateTime.now());

		            repository.save(event);
		        }
		    }
		}
