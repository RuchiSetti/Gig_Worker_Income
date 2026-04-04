package com.guidewire.gigsuraksha.service;
import java.util.UUID;
public interface risksnapshotservice {
	

	

	

	  void generateWeeklySnapshot(UUID zoneId);

	    void getZoneExposure(UUID zoneId);

	    void flagHighRiskZones(UUID snapshotId);

	
}
