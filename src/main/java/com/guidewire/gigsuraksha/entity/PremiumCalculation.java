package com.guidewire.gigsuraksha.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
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
    private BigDecimal kFactor;

    @Column(precision = 10, scale = 2)
    private BigDecimal expectedWeeklyIncome;

    @Column(precision = 4, scale = 3)
    private BigDecimal riskFactorR;

    @Column(precision = 4, scale = 3)
    private BigDecimal variabilityV;

    @Column(precision = 4, scale = 3)
    private BigDecimal coverageMultiplierC;

    @Column(precision = 4, scale = 3)
    private BigDecimal crf;

    @Column(precision = 10, scale = 2)
    private BigDecimal finalPremium;

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

    public BigDecimal getKFactor() {
        return kFactor;
    }

    public void setKFactor(BigDecimal kFactor) {
        this.kFactor = kFactor;
    }

    public BigDecimal getExpectedWeeklyIncome() {
        return expectedWeeklyIncome;
    }

    public void setExpectedWeeklyIncome(BigDecimal expectedWeeklyIncome) {
        this.expectedWeeklyIncome = expectedWeeklyIncome;
    }

    public BigDecimal getRiskFactorR() {
        return riskFactorR;
    }

    public void setRiskFactorR(BigDecimal riskFactorR) {
        this.riskFactorR = riskFactorR;
    }

    public BigDecimal getVariabilityV() {
        return variabilityV;
    }

    public void setVariabilityV(BigDecimal variabilityV) {
        this.variabilityV = variabilityV;
    }

    public BigDecimal getCoverageMultiplierC() {
        return coverageMultiplierC;
    }

    public void setCoverageMultiplierC(BigDecimal coverageMultiplierC) {
        this.coverageMultiplierC = coverageMultiplierC;
    }

    public BigDecimal getCrf() {
        return crf;
    }

    public void setCrf(BigDecimal crf) {
        this.crf = crf;
    }

    public BigDecimal getFinalPremium() {
        return finalPremium;
    }

    public void setFinalPremium(BigDecimal finalPremium) {
        this.finalPremium = finalPremium;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}