package com.guidewire.gigsuraksha.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.IncomeProfile;
import com.guidewire.gigsuraksha.entity.IncomeRecord;
import com.guidewire.gigsuraksha.repository.incomeprofilerepository;
import com.guidewire.gigsuraksha.repository.incomerecordrepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;






@Service
public class incomerecordserviceimpl implements incomerecordservice {

    @Autowired
    private incomerecordrepository repository;
    @Autowired
    private incomeprofilerepository profileRepo;
    @Override
    public void fetchActualIncome(UUID partnerId, LocalDate date) {

        IncomeRecord record = new IncomeRecord();

        record.setPartnerId(partnerId);
        record.setRecordDate(date);
        record.setActualIncome(new BigDecimal("500.0"));
        record.setExpectedIncome(new BigDecimal("600.0"));
        record.setIncomeLoss(new BigDecimal("100.0"));
        record.setZScore(new BigDecimal("0.0"));
        record.setOrdersCompleted(10);
        record.setHoursActive(new BigDecimal("8.0"));
        record.setDataSource("platform_api");

        repository.save(record);
    }

    @Override
    public void computeZScore(UUID partnerId, LocalDate date) {

        IncomeRecord record = repository
            .findByPartnerIdAndRecordDate(partnerId, date)
            .orElse(null);

        IncomeProfile profile = profileRepo
            .findByPartnerId(partnerId)
            .orElse(null);

        if (record == null || profile == null) return;

        BigDecimal diff = record.getActualIncome().subtract(profile.getPredictedDailyIncome());
        BigDecimal z = diff.divide(profile.getStdDeviation(), 6, RoundingMode.HALF_UP); // scale 6, adjust if needed

        record.setZScore(z);
        repository.save(record);
    }

    @Override
    public void flagSignificantDrop(UUID partnerId, LocalDate date) {

        Optional<IncomeRecord> optional =
                repository.findByPartnerIdAndRecordDate(partnerId, date);

        if (optional.isPresent()) {
            IncomeRecord record = optional.get();

            if (record.getZScore() != null && record.getZScore().compareTo(new BigDecimal("-2")) < 0) {
                record.setIncomeLoss(
                    record.getExpectedIncome().subtract(record.getActualIncome())
                );
            }

            repository.save(record);
        }
    }
}