package com.guidewire.gigsuraksha.entity;



import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class PremiumCalculation {

    @Id
    @GeneratedValue
    private UUID calcId;

    private UUID partnerId;

    private LocalDate weekStart;

    @Column(precision = 6, scale = 4)
    private Double kFactor;

    @Column(precision = 10, scale = 2)
    private Double expectedWeeklyIncome;

    @Column(precision = 4, scale = 3)
    private Double riskFactorR;

    @Column(precision = 4, scale = 3)
    private Double variabilityV;

    @Column(precision = 4, scale = 3)
    private Double coverageMultiplierC;

    @Column(precision = 4, scale = 3)
    private Double crf;

    @Column(precision = 10, scale = 2)
    private Double finalPremium;

    private LocalDateTime calculatedAt;

    // getters & setters

    public UUID getCalcId() {
        return calcId;
    }

    public void setCalcId(UUID calcId) {
        this.calcId = calcId;
    }

    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partnerId) {
        this.partnerId = partnerId;
    }

    public LocalDate getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(LocalDate weekStart) {
        this.weekStart = weekStart;
    }

    public Double getKFactor() {
        return kFactor;
    }

    public void setKFactor(Double kFactor) {
        this.kFactor = kFactor;
    }

    public Double getExpectedWeeklyIncome() {
        return expectedWeeklyIncome;
    }

    public void setExpectedWeeklyIncome(Double expectedWeeklyIncome) {
        this.expectedWeeklyIncome = expectedWeeklyIncome;
    }

    public Double getRiskFactorR() {
        return riskFactorR;
    }

    public void setRiskFactorR(Double riskFactorR) {
        this.riskFactorR = riskFactorR;
    }

    public Double getVariabilityV() {
        return variabilityV;
    }

    public void setVariabilityV(Double variabilityV) {
        this.variabilityV = variabilityV;
    }

    public Double getCoverageMultiplierC() {
        return coverageMultiplierC;
    }

    public void setCoverageMultiplierC(Double coverageMultiplierC) {
        this.coverageMultiplierC = coverageMultiplierC;
    }

    public Double getCrf() {
        return crf;
    }

    public void setCrf(Double crf) {
        this.crf = crf;
    }

    public Double getFinalPremium() {
        return finalPremium;
    }

    public void setFinalPremium(Double finalPremium) {
        this.finalPremium = finalPremium;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
