package com.guidewire.gigsuraksha.service;
import java.util.UUID;

import com.guidewire.gigsuraksha.entity.ManualTriggerOverride;

public interface manualtriggeroverrideservice {
	
	

	   void createOverride(UUID zoneId, String reason);

	    void expireOverride(UUID overrideId);

	    ManualTriggerOverride getActiveOverrides(UUID overrideId);
	}
