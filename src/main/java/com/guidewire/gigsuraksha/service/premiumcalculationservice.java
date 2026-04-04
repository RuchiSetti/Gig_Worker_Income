package com.guidewire.gigsuraksha.service;


import java.time.LocalDate;
import java.util.UUID;

public interface premiumcalculationservice {

    void computePremium(UUID partnerId,
    LocalDate week,
    Double expectedWeeklyIncome,
    Double riskFactorR,
    Double variabilityV,
    Double coverageMultiplierC,
    Double crf);

void getBreakdown(UUID calcId);
    
    
}