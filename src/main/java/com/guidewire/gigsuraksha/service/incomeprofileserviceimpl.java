package com.guidewire.gigsuraksha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.IncomeProfile;
import com.guidewire.gigsuraksha.repository.incomeprofilerepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class incomeprofileserviceimpl implements incomeprofileservice {

    @Autowired
    private incomeprofilerepository repository;

    @Override
    public void buildProfile(UUID partnerId) {

        IncomeProfile profile = new IncomeProfile();
        profile.setPartnerId(partnerId);
        profile.setPredictedDailyIncome(new BigDecimal("500.00"));
        profile.setPredictedWeeklyIncome(new BigDecimal("3500.00"));
        profile.setStdDeviation(new BigDecimal("50.00"));
        profile.setIncomeVariabilityScore(new BigDecimal("0.500"));
        profile.setModelVersion("v1");
        profile.setLastUpdatedAt(LocalDateTime.now());
        profile.setTrainingDataPoints(100);

        repository.save(profile);
    }

    @Override
    public BigDecimal predictDailyIncome(UUID partnerId, LocalDate date) {
        Optional<IncomeProfile> optionalProfile = repository.findByPartnerId(partnerId);

        return optionalProfile
                .map(IncomeProfile::getPredictedDailyIncome)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public void refreshModel(UUID partnerId) {
        repository.findByPartnerId(partnerId).ifPresent(profile -> {
            profile.setModelVersion("v2");
            profile.setLastUpdatedAt(LocalDateTime.now());
            repository.save(profile);
        });
    }
}