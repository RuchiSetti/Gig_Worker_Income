package com.guidewire.gigsuraksha.service;

import java.util.UUID;
public interface claimservice {
	
	 void initiateClaim(UUID triggerId, UUID partnerId, UUID planId); 
	void evaluateLoss(UUID claimId);

    void runFraudCheck(UUID claimId);

    void approveClaim(UUID claimId);

    void rejectClaim(UUID claimId, String reason);
	}
