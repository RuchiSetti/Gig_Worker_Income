package com.guidewire.gigsuraksha.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.IncomeProfile;
import com.guidewire.gigsuraksha.repository.incomeprofilerepository;

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
        profile.setPredictedDailyIncome(500.0);
        profile.setPredictedWeeklyIncome(3500.0);
        profile.setStdDeviation(50.0);
        profile.setIncomeVariabilityScore(0.5);
        profile.setModelVersion("v1");
        profile.setLastUpdatedAt(LocalDateTime.now());
        profile.setTrainingDataPoints(100);

        repository.save(profile);
    }

    @Override
    public Double predictDailyIncome(UUID partnerId, LocalDate date) {

        Optional<IncomeProfile> optional = repository.findByPartnerId(partnerId);

        if (optional.isPresent()) {
            return optional.get().getPredictedDailyIncome();
        }

        return 0.0;
    }

    @Override
    public void refreshModel(UUID partnerId) {

        Optional<IncomeProfile> optional = repository.findByPartnerId(partnerId);

        if (optional.isPresent()) {
            IncomeProfile profile = optional.get();

            profile.setModelVersion("v2");
            profile.setLastUpdatedAt(LocalDateTime.now());

            repository.save(profile);
        }
    }
}