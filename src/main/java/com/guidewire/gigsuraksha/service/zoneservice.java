package com.guidewire.gigsuraksha.service;

import java.util.UUID;

public interface zoneservice {
	
	

	void getZoneRiskFactor(UUID zoneId);

    void updateRiskScore(UUID zoneId);

    void getActivePartners(UUID zoneId);
	
}
