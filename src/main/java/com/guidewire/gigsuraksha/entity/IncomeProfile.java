package com.guidewire.gigsuraksha.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class IncomeProfile {

    @Id
    @GeneratedValue
    private UUID profileId;

    private UUID partnerId;

    @Column(precision = 10, scale = 2)
    private BigDecimal predictedDailyIncome;

    @Column(precision = 10, scale = 2)
    private BigDecimal predictedWeeklyIncome;

    @Column(precision = 10, scale = 2)
    private BigDecimal stdDeviation;

    @Column(precision = 4, scale = 3)
    private BigDecimal incomeVariabilityScore;

    @Column(length = 20)
    private String modelVersion;

    private LocalDateTime lastUpdatedAt;

    private Integer trainingDataPoints;

    // getters & setters

    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partnerId) {
        this.partnerId = partnerId;
    }

    public BigDecimal getPredictedDailyIncome() {
        return predictedDailyIncome;
    }

    public void setPredictedDailyIncome(BigDecimal predictedDailyIncome) {
        this.predictedDailyIncome = predictedDailyIncome;
    }

    public BigDecimal getPredictedWeeklyIncome() {
        return predictedWeeklyIncome;
    }

    public void setPredictedWeeklyIncome(BigDecimal predictedWeeklyIncome) {
        this.predictedWeeklyIncome = predictedWeeklyIncome;
    }

    public BigDecimal getStdDeviation() {
        return stdDeviation;
    }

    public void setStdDeviation(BigDecimal stdDeviation) {
        this.stdDeviation = stdDeviation;
    }

    public BigDecimal getIncomeVariabilityScore() {
        return incomeVariabilityScore;
    }

    public void setIncomeVariabilityScore(BigDecimal incomeVariabilityScore) {
        this.incomeVariabilityScore = incomeVariabilityScore;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Integer getTrainingDataPoints() {
        return trainingDataPoints;
    }

    public void setTrainingDataPoints(Integer trainingDataPoints) {
        this.trainingDataPoints = trainingDataPoints;
    }
}