package com.guidewire.gigsuraksha.service;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.Claim;
import com.guidewire.gigsuraksha.entity.FraudFlag;
import com.guidewire.gigsuraksha.repository.claimrepository;
import com.guidewire.gigsuraksha.repository.fraudflagrepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
	import java.util.Optional;
	import java.util.UUID;

	@Service
	public class fraudflagserviceimpl implements fraudflagservice {

		 @Autowired
		    private fraudflagrepository repository;
		 @Autowired
		 private claimrepository claimRepo;
		 @Override
		 public void runRuleChecks(UUID claimId) {

		     Claim claim = claimRepo.findById(claimId).orElse(null);

		     if (claim == null) return;

		     FraudFlag flag = new FraudFlag();

		     flag.setClaimId(claimId);
		     flag.setPartnerId(claim.getPartnerId());
		     flag.setDetectionLayer("rule_based");

		     // example logic
		     flag.setFraudProbability(new BigDecimal("0.6"));

		     if (flag.getFraudProbability().compareTo(new BigDecimal("0.7")) > 0) {
		         flag.setActionTaken("flagged");
		     } else {
		         flag.setActionTaken("clean");
		     }

		     flag.setFlaggedAt(LocalDateTime.now());

		     repository.save(flag);
		 }
		    @Override
		    public void scoreXGBoost(UUID flagId, String features) {

		        Optional<FraudFlag> optional = repository.findById(flagId);

		        if (optional.isPresent()) {
		            FraudFlag flag = optional.get();

		            flag.setDetectionLayer("xgboost");
		            flag.setFraudProbability(new BigDecimal("0.7"));

		            repository.save(flag);
		        }
		    }

		    @Override
		    public void runIsolationForest(UUID flagId) {

		        Optional<FraudFlag> optional = repository.findById(flagId);

		        if (optional.isPresent()) {
		            FraudFlag flag = optional.get();

		            flag.setDetectionLayer("isolation_forest");
		            flag.setFraudProbability(new BigDecimal("0.5"));

		            repository.save(flag);
		        }
		    }

		    @Override
		    public void resolveFlag(UUID flagId, UUID adminId) {

		        Optional<FraudFlag> optional = repository.findById(flagId);

		        if (optional.isPresent()) {
		            FraudFlag flag = optional.get();

		            flag.setActionTaken("cleared");
		            flag.setReviewedBy(adminId); // ✅ ADMIN LINK
		            flag.setReviewNote("Reviewed and cleared");
		            flag.setFlaggedAt(LocalDateTime.now());

		            repository.save(flag);
		        }
		    }
		}