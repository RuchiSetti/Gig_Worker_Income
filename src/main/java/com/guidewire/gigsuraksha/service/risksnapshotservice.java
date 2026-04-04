package com.guidewire.gigsuraksha.service;
import java.math.BigDecimal;
import java.util.UUID;
public interface risksnapshotservice {
	

	

	

	void generateWeeklySnapshot(UUID zoneId);

    BigDecimal getZoneExposure(UUID zoneId);

    void flagHighRiskZones(UUID snapshotId);
	
}
