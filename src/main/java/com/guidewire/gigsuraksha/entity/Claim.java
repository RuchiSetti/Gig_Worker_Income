package com.guidewire.gigsuraksha.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Claim {

    @Id
    @GeneratedValue
    private UUID claimId;

    private UUID partnerId;

    private UUID planId;

    private UUID triggerEventId;

    private LocalDate claimDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal incomeLoss;

    private Integer coveragePercentage;

    @Column(precision = 4, scale = 3)
    private BigDecimal trustScoreAtClaim;

    @Column(precision = 10, scale = 2)
    private BigDecimal finalPayout;

    private String status; // pending | approved | rejected | flagged

    @Column(precision = 4, scale = 3)
    private BigDecimal fraudProbability;

    private LocalDateTime initiatedAt;

    private LocalDateTime resolvedAt;

    // getters & setters

    public UUID getClaimId() {
        return claimId;
    }

    public void setClaimId(UUID claimId) {
        this.claimId = claimId;
    }

    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partnerId) {
        this.partnerId = partnerId;
    }

    public UUID getPlanId() {
        return planId;
    }

    public void setPlanId(UUID planId) {
        this.planId = planId;
    }

    public UUID getTriggerEventId() {
        return triggerEventId;
    }

    public void setTriggerEventId(UUID triggerEventId) {
        this.triggerEventId = triggerEventId;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public BigDecimal getIncomeLoss() {
        return incomeLoss;
    }

    public void setIncomeLoss(BigDecimal incomeLoss) {
        this.incomeLoss = incomeLoss;
    }

    public Integer getCoveragePercentage() {
        return coveragePercentage;
    }

    public void setCoveragePercentage(Integer coveragePercentage) {
        this.coveragePercentage = coveragePercentage;
    }

    public BigDecimal getTrustScoreAtClaim() {
        return trustScoreAtClaim;
    }

    public void setTrustScoreAtClaim(BigDecimal trustScoreAtClaim) {
        this.trustScoreAtClaim = trustScoreAtClaim;
    }

    public BigDecimal getFinalPayout() {
        return finalPayout;
    }

    public void setFinalPayout(BigDecimal finalPayout) {
        this.finalPayout = finalPayout;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getFraudProbability() {
        return fraudProbability;
    }

    public void setFraudProbability(BigDecimal fraudProbability) {
        this.fraudProbability = fraudProbability;
    }

    public LocalDateTime getInitiatedAt() {
        return initiatedAt;
    }

    public void setInitiatedAt(LocalDateTime initiatedAt) {
        this.initiatedAt = initiatedAt;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }
}