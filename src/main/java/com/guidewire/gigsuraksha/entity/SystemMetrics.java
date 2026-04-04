package com.guidewire.gigsuraksha.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class SystemMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID metricId;

    private LocalDate periodDate;

    private Integer totalActivePartners;

    @Column(precision = 14, scale = 2)
    private BigDecimal totalPremiumsCollected;

    @Column(precision = 14, scale = 2)
    private BigDecimal totalPayoutsDisbursed;

    @Column(precision = 6, scale = 4)
    private BigDecimal lossRatio;

    @Column(precision = 6, scale = 4)
    private BigDecimal claimApprovalRate;

    @Column(precision = 6, scale = 4)
    private BigDecimal fraudFlagRate;

    @Column(precision = 10, scale = 2)
    private BigDecimal avgPayoutAmount;

    private Integer triggerEventsCount;

    private LocalDateTime computedAt;

    // Getters & Setters
    public UUID getMetricId() {
        return metricId;
    }

    public void setMetricId(UUID metricId) {
        this.metricId = metricId;
    }

    public LocalDate getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(LocalDate periodDate) {
        this.periodDate = periodDate;
    }

    public Integer getTotalActivePartners() {
        return totalActivePartners;
    }

    public void setTotalActivePartners(Integer totalActivePartners) {
        this.totalActivePartners = totalActivePartners;
    }

    public BigDecimal getTotalPremiumsCollected() {
        return totalPremiumsCollected;
    }

    public void setTotalPremiumsCollected(BigDecimal totalPremiumsCollected) {
        this.totalPremiumsCollected = totalPremiumsCollected;
    }

    public BigDecimal getTotalPayoutsDisbursed() {
        return totalPayoutsDisbursed;
    }

    public void setTotalPayoutsDisbursed(BigDecimal totalPayoutsDisbursed) {
        this.totalPayoutsDisbursed = totalPayoutsDisbursed;
    }

    public BigDecimal getLossRatio() {
        return lossRatio;
    }

    public void setLossRatio(BigDecimal lossRatio) {
        this.lossRatio = lossRatio;
    }

    public BigDecimal getClaimApprovalRate() {
        return claimApprovalRate;
    }

    public void setClaimApprovalRate(BigDecimal claimApprovalRate) {
        this.claimApprovalRate = claimApprovalRate;
    }

    public BigDecimal getFraudFlagRate() {
        return fraudFlagRate;
    }

    public void setFraudFlagRate(BigDecimal fraudFlagRate) {
        this.fraudFlagRate = fraudFlagRate;
    }

    public BigDecimal getAvgPayoutAmount() {
        return avgPayoutAmount;
    }

    public void setAvgPayoutAmount(BigDecimal avgPayoutAmount) {
        this.avgPayoutAmount = avgPayoutAmount;
    }

    public Integer getTriggerEventsCount() {
        return triggerEventsCount;
    }

    public void setTriggerEventsCount(Integer triggerEventsCount) {
        this.triggerEventsCount = triggerEventsCount;
    }

    public LocalDateTime getComputedAt() {
        return computedAt;
    }

    public void setComputedAt(LocalDateTime computedAt) {
        this.computedAt = computedAt;
    }
}