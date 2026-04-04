package com.guidewire.gigsuraksha.entity;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class SystemMetrics {

    @Id
    @GeneratedValue
    private UUID metricId;

    private LocalDate periodDate;

    private Integer totalActivePartners;

    @Column(precision = 14, scale = 2)
    private Double totalPremiumsCollected;

    @Column(precision = 14, scale = 2)
    private Double totalPayoutsDisbursed;

    @Column(precision = 6, scale = 4)
    private Double lossRatio;

    @Column(precision = 6, scale = 4)
    private Double claimApprovalRate;

    @Column(precision = 6, scale = 4)
    private Double fraudFlagRate;

    @Column(precision = 10, scale = 2)
    private Double avgPayoutAmount;

    private Integer triggerEventsCount;

    private LocalDateTime computedAt;

    // getters & setters

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

    public Double getTotalPremiumsCollected() {
        return totalPremiumsCollected;
    }

    public void setTotalPremiumsCollected(Double totalPremiumsCollected) {
        this.totalPremiumsCollected = totalPremiumsCollected;
    }

    public Double getTotalPayoutsDisbursed() {
        return totalPayoutsDisbursed;
    }

    public void setTotalPayoutsDisbursed(Double totalPayoutsDisbursed) {
        this.totalPayoutsDisbursed = totalPayoutsDisbursed;
    }

    public Double getLossRatio() {
        return lossRatio;
    }

    public void setLossRatio(Double lossRatio) {
        this.lossRatio = lossRatio;
    }

    public Double getClaimApprovalRate() {
        return claimApprovalRate;
    }

    public void setClaimApprovalRate(Double claimApprovalRate) {
        this.claimApprovalRate = claimApprovalRate;
    }

    public Double getFraudFlagRate() {
        return fraudFlagRate;
    }

    public void setFraudFlagRate(Double fraudFlagRate) {
        this.fraudFlagRate = fraudFlagRate;
    }

    public Double getAvgPayoutAmount() {
        return avgPayoutAmount;
    }

    public void setAvgPayoutAmount(Double avgPayoutAmount) {
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