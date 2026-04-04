package com.guidewire.gigsuraksha.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.IncomeProfile;
import com.guidewire.gigsuraksha.entity.InsurancePlan;
import com.guidewire.gigsuraksha.repository.incomeprofilerepository;
import com.guidewire.gigsuraksha.repository.insuranceplanrepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
@Service
public class insuranceplanserviceimpl implements insuranceplanservice {

    @Autowired
    private insuranceplanrepository repository;

    @Override
    public void calculatePremium(UUID partnerId) {

        InsurancePlan plan = new InsurancePlan();

        plan.setPartnerId(partnerId);
        plan.setCoverageTier("basic");
        plan.setCoveragePercentage(30);
        plan.setWeeklyPremium(new BigDecimal("100.0"));
        plan.setWeekStartDate(LocalDate.now());
        plan.setWeekEndDate(LocalDate.now().plusDays(7));
        plan.setStatus("active");
        plan.setPaymentReference("PAY123");
        plan.setCreatedAt(LocalDateTime.now());

        repository.save(plan);
    }

    @Override
    public void activatePlan(UUID planId) {

        Optional<InsurancePlan> optional = repository.findById(planId);

        if (optional.isPresent()) {
            InsurancePlan plan = optional.get();
            plan.setStatus("active");
            repository.save(plan);
        }
    }

    @Override
    public void renewWeekly(UUID planId) {

        Optional<InsurancePlan> optional = repository.findById(planId);

        if (optional.isPresent()) {
            InsurancePlan plan = optional.get();

            plan.setWeekStartDate(LocalDate.now());
            plan.setWeekEndDate(LocalDate.now().plusDays(7));
            plan.setStatus("active");

            repository.save(plan);
        }
    }

    @Override
    public void cancelPlan(UUID planId) {

        Optional<InsurancePlan> optional = repository.findById(planId);

        if (optional.isPresent()) {
            InsurancePlan plan = optional.get();
            plan.setStatus("cancelled");
            repository.save(plan);
        }
    }
}
