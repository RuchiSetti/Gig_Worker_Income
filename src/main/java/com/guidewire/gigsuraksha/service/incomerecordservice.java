package com.guidewire.gigsuraksha.service;
import java.time.LocalDate;
import java.util.UUID;
public interface incomerecordservice {
	
	

    void fetchActualIncome(UUID partnerId, LocalDate date);

    void computeZScore(UUID partnerId, LocalDate date);

    void flagSignificantDrop(UUID partnerId, LocalDate date);
	
}
