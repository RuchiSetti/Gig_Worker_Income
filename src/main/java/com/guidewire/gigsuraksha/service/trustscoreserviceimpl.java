package com.guidewire.gigsuraksha.service;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.TrustScore;
import com.guidewire.gigsuraksha.repository.claimrepository;
import com.guidewire.gigsuraksha.repository.trustscorerepository;

import java.time.LocalDateTime;
	import java.util.Optional;
	import java.util.UUID;

	@Service
	public class trustscoreserviceimpl implements trustscoreservice {

		@Autowired
	    private trustscorerepository repository;
		@Autowired
		private claimrepository claimRepo;
		@Override
		public void applyDelta(UUID partnerId, String reason, Double amount) {

		    TrustScore trust = repository.findByPartnerId(partnerId).orElse(null);

		    if (trust == null) return;

		    Double current = trust.getCurrentScore() == null ? 1.0 : trust.getCurrentScore();

		    trust.setCurrentScore(current + amount);
		    trust.setLastEvent(reason);
		    trust.setUpdatedAt(LocalDateTime.now());

		    repository.save(trust);
		}

	    @Override
	    public Double getCurrentScore(UUID partnerId) {

	        Optional<TrustScore> optional = repository.findById(partnerId);

	        if (optional.isPresent()) {
	            return optional.get().getCurrentScore();
	        }

	        return 0.0;
	    }

	    @Override
	    public void checkSuspension(UUID partnerId) {

	        Optional<TrustScore> optional = repository.findById(partnerId);

	        if (optional.isPresent()) {
	            TrustScore trust = optional.get();

	            if (trust.getCurrentScore() != null && trust.getCurrentScore() < 0.3) {
	                trust.setIsSuspended(true);
	            }

	            repository.save(trust);
	        }
	    }

	    @Override
	    public String getLabel(UUID partnerId) {

	        Optional<TrustScore> optional = repository.findById(partnerId);

	        if (optional.isPresent()) {
	            TrustScore trust = optional.get();

	            Double score = trust.getCurrentScore();

	            if (score != null) {
	                if (score >= 0.8) return "excellent";
	                if (score >= 0.6) return "good";
	                if (score >= 0.4) return "average";
	                return "risk";
	            }
	        }

	        return "unknown";
	    }

		
	
	}