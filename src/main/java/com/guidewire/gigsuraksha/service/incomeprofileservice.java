package com.guidewire.gigsuraksha.service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
public interface incomeprofileservice {

	void buildProfile(UUID partnerId);

    BigDecimal predictDailyIncome(UUID partnerId, LocalDate date);

    void refreshModel(UUID partnerId);
	}

