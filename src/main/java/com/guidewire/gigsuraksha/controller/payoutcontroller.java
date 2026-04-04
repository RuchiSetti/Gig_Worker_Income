package com.guidewire.gigsuraksha.controller;


	import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.entity.PayOut;
import com.guidewire.gigsuraksha.service.payoutservice;

@RestController
@RequestMapping("/api/payout")
public class payoutcontroller {

	   @Autowired
	    private payoutservice service;

	   @PostMapping("/initiate/{claimId}")
	   public String initiateUPIPayout(@PathVariable UUID claimId) {
	       service.initiateUPIPayout(claimId);
	       return "Payout Initiated";
	   }
	    @PostMapping("/verify/{payoutId}")
	    public String verifyPaymentStatus(@PathVariable UUID payoutId) {
	        service.verifyPaymentStatus(payoutId);
	        return "Payment Verified";
	    }

	    @PostMapping("/retry/{payoutId}")
	    public String retryFailedPayout(@PathVariable UUID payoutId) {
	        service.retryFailedPayout(payoutId);
	        return "Retry Attempted";
	    }
	}
