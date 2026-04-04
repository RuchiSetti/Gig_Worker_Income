package com.guidewire.gigsuraksha.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.IncomeProfile;
import com.guidewire.gigsuraksha.entity.IncomeRecord;
import com.guidewire.gigsuraksha.repository.incomeprofilerepository;
import com.guidewire.gigsuraksha.repository.incomerecordrepository;

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
        record.setActualIncome(500.0);
        record.setExpectedIncome(600.0);
        record.setIncomeLoss(100.0);
        record.setZScore(0.0);
        record.setOrdersCompleted(10);
        record.setHoursActive(8.0);
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

        Double z = (record.getActualIncome() - profile.getPredictedDailyIncome())
                / profile.getStdDeviation();

        record.setZScore(z);

        repository.save(record);
    }

    @Override
    public void flagSignificantDrop(UUID partnerId, LocalDate date) {

        Optional<IncomeRecord> optional =
                repository.findByPartnerIdAndRecordDate(partnerId, date);

        if (optional.isPresent()) {
            IncomeRecord record = optional.get();

            if (record.getZScore() != null && record.getZScore() < -2) {
                record.setIncomeLoss(
                        record.getExpectedIncome() - record.getActualIncome()
                );
            }

            repository.save(record);
        }
    }
}