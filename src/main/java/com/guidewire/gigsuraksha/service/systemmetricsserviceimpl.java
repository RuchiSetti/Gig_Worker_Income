package com.guidewire.gigsuraksha.service;

import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.PayOut;
import com.guidewire.gigsuraksha.entity.SystemMetrics;
import com.guidewire.gigsuraksha.repository.payoutrepository;
import com.guidewire.gigsuraksha.repository.systemmetricsrepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class systemmetricsserviceimpl implements systemmetricsservice {

    private final payoutrepository payoutRepo;
    private final systemmetricsrepository repository;

    // ✅ constructor injection
    public systemmetricsserviceimpl(payoutrepository payoutRepo,
                                    systemmetricsrepository repository) {
        this.payoutRepo = payoutRepo;
        this.repository = repository;
    }

    @Override
    public void computeDailyMetrics() {

        SystemMetrics metrics = new SystemMetrics();
        metrics.setMetricId(UUID.randomUUID());
        metrics.setPeriodDate(LocalDate.now());

        // 🔥 REAL DATA: sum payouts as BigDecimal
        BigDecimal totalPayout = payoutRepo.findAll().stream()
                .map(p -> p.getAmount() == null ? BigDecimal.ZERO : p.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        metrics.setTotalPayoutsDisbursed(totalPayout);
        metrics.setComputedAt(LocalDateTime.now());

        repository.save(metrics);
    }

    @Override
    public List<SystemMetrics> getLossRatioDashboard(LocalDate date) {
        return repository.findAll().stream()
                .filter(m -> m.getPeriodDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<SystemMetrics> exportReport(LocalDate period) {
        return repository.findAll().stream()
                .filter(m -> m.getPeriodDate().equals(period))
                .collect(Collectors.toList());
    }
}