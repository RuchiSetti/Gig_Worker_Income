package com.guidewire.gigsuraksha.service;

import java.util.UUID;

public interface insuranceplanservice {
	

    void calculatePremium(UUID partnerId);

    void activatePlan(UUID planId);

    void renewWeekly(UUID planId);

    void cancelPlan(UUID planId);
	
}
