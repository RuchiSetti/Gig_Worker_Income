package com.guidewire.gigsuraksha.service;

import java.math.BigDecimal;
import java.util.UUID;

import com.guidewire.gigsuraksha.entity.Zone;

public interface zoneservice {
	
	

	// Returns the risk factor of a zone
    BigDecimal getZoneRiskFactor(UUID zoneId);

    // Updates the risk factor of a zone
    void updateRiskScore(UUID zoneId, double newRiskScore);

    // Returns the Zone entity (can be used to get active partners, name, etc.)
    Zone getActivePartners(UUID zoneId);
	
}
