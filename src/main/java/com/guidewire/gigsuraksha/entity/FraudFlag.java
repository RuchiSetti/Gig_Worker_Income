package com.guidewire.gigsuraksha.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class FraudFlag {

    @Id
    @GeneratedValue
    private UUID flagId;

    private UUID claimId;

    private UUID partnerId;

    private String detectionLayer; // rule_based | xgboost | isolation_forest

    @Column(precision = 4, scale = 3)
    private BigDecimal fraudProbability;

    @Column(length = 100)
    private String ruleTriggered;

    @Column(precision = 4, scale = 3)
    private BigDecimal gpsConsistencyScore;

    private String actionTaken; // auto_rejected | manual_review | cleared

    private UUID reviewedBy;

    @Column(columnDefinition = "TEXT")
    private String reviewNote;

    private LocalDateTime flaggedAt;

    // getters & setters

    public UUID getFlagId() {
        return flagId;
    }

    public void setFlagId(UUID flagId) {
        this.flagId = flagId;
    }

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

    public String getDetectionLayer() {
        return detectionLayer;
    }

    public void setDetectionLayer(String detectionLayer) {
        this.detectionLayer = detectionLayer;
    }

    public BigDecimal getFraudProbability() {
        return fraudProbability;
    }

    public void setFraudProbability(BigDecimal fraudProbability) {
        this.fraudProbability = fraudProbability;
    }

    public String getRuleTriggered() {
        return ruleTriggered;
    }

    public void setRuleTriggered(String ruleTriggered) {
        this.ruleTriggered = ruleTriggered;
    }

    public BigDecimal getGpsConsistencyScore() {
        return gpsConsistencyScore;
    }

    public void setGpsConsistencyScore(BigDecimal gpsConsistencyScore) {
        this.gpsConsistencyScore = gpsConsistencyScore;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public UUID getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(UUID reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public String getReviewNote() {
        return reviewNote;
    }

    public void setReviewNote(String reviewNote) {
        this.reviewNote = reviewNote;
    }

    public LocalDateTime getFlaggedAt() {
        return flaggedAt;
    }

    public void setFlaggedAt(LocalDateTime flaggedAt) {
        this.flaggedAt = flaggedAt;
    }
}