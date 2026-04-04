package com.guidewire.gigsuraksha.service;
import java.util.UUID;
public interface fraudflagservice {
	

	void runRuleChecks(UUID claimId);

    void scoreXGBoost(UUID flagId, String features);

    void runIsolationForest(UUID flagId);

    void resolveFlag(UUID flagId, UUID adminId);
	
}
