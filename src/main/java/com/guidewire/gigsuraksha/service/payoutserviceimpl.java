package com.guidewire.gigsuraksha.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.Claim;
import com.guidewire.gigsuraksha.entity.PayOut;
import com.guidewire.gigsuraksha.repository.claimrepository;
import com.guidewire.gigsuraksha.repository.payoutrepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class payoutserviceimpl implements payoutservice {

	 @Autowired
	    private payoutrepository repository;
	 @Autowired
	 private claimrepository claimRepo;
	 @Override
	 public void initiateUPIPayout(UUID claimId) {

	     Claim claim = claimRepo.findById(claimId).orElse(null);

	     if (claim == null) return;

	     PayOut payout = new PayOut();

	     payout.setClaimId(claimId);
	     payout.setPartnerId(claim.getPartnerId());
	     payout.setAmount(claim.getFinalPayout());
	     payout.setUpiId("from_delivery_partner");
	     payout.setStatus("initiated");
	     payout.setInitiatedAt(LocalDateTime.now());

	     repository.save(payout);
	 }
	    @Override
	    public void verifyPaymentStatus(UUID payoutId) {

	        Optional<PayOut> optional = repository.findById(payoutId);

	        if (optional.isPresent()) {
	        	PayOut payout = optional.get();

	            payout.setStatus("success");
	            payout.setCreditedAt(LocalDateTime.now());

	            repository.save(payout);
	        }
	    }

	    @Override
	    public void retryFailedPayout(UUID payoutId) {

	        Optional<PayOut> optional = repository.findById(payoutId);

	        if (optional.isPresent()) {
	        	PayOut payout = optional.get();

	            if ("failed".equals(payout.getStatus())) {
	                payout.setStatus("initiated");
	                payout.setFailureReason(null);
	                payout.setInitiatedAt(LocalDateTime.now());
	            }

	            repository.save(payout);
	        }
	    }
	}
