package com.guidewire.gigsuraksha.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.PremiumCalculation;
import com.guidewire.gigsuraksha.repository.premiumcalculationrepository;

@Service
public class premiumcalculationserviceimpl implements premiumcalculationservice {

    @Autowired
    private premiumcalculationrepository repository;

    private static final Double K_FACTOR = 0.025; // from your model

    @Override
    public void computePremium(UUID partnerId,
                               LocalDate week,
                               Double expectedWeeklyIncome,
                               Double riskFactorR,
                               Double variabilityV,
                               Double coverageMultiplierC,
                               Double crf) {

        PremiumCalculation calc = new PremiumCalculation();

        calc.setCalcId(UUID.randomUUID());
        calc.setPartnerId(partnerId);
        calc.setWeekStart(week);

        calc.setKFactor(BigDecimal.valueOf(K_FACTOR));
        calc.setExpectedWeeklyIncome(BigDecimal.valueOf(expectedWeeklyIncome));
        calc.setRiskFactorR(BigDecimal.valueOf(riskFactorR));
        calc.setVariabilityV(BigDecimal.valueOf(variabilityV));
        calc.setCoverageMultiplierC(BigDecimal.valueOf(coverageMultiplierC));
        calc.setCrf(BigDecimal.valueOf(crf));

        // 🔥 REAL FORMULA IMPLEMENTATION
        Double premium = K_FACTOR
                * expectedWeeklyIncome
                * riskFactorR
                * variabilityV
                * coverageMultiplierC
                * crf;

        calc.setFinalPremium(BigDecimal.valueOf(premium));
        calc.setCalculatedAt(LocalDateTime.now());

        repository.save(calc);
    }

    @Override
    public void getBreakdown(UUID calcId) {

        Optional<PremiumCalculation> optional = repository.findById(calcId);

        if (optional.isPresent()) {
            PremiumCalculation calc = optional.get();

            // you can log or return later (keeping your structure same)
            calc.getFinalPremium();
        }
    }
}