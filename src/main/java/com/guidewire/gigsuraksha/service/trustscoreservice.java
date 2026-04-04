package com.guidewire.gigsuraksha.service;

import java.math.BigDecimal;
import java.util.UUID;


public interface trustscoreservice {

	void applyDelta(UUID partnerId, String reason, Double amount);

    BigDecimal getCurrentScore(UUID partnerId);

    void checkSuspension(UUID partnerId);

    String getLabel(UUID partnerId);
}
