package com.guidewire.gigsuraksha.service;
import java.time.LocalDate;
import java.util.UUID;
public interface incomeprofileservice {

	void buildProfile(UUID partnerId);

    Double predictDailyIncome(UUID partnerId, LocalDate date);

    void refreshModel(UUID partnerId);
	}

