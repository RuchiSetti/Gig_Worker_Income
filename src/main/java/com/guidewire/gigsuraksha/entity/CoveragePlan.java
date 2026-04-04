package com.guidewire.gigsuraksha.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class CoveragePlan {

    @Id
    @GeneratedValue
    private UUID planId;

    private UUID partnerId;

    private String coverageTier; // basic | standard | premium

    private Integer coveragePercentage; // 30 | 60 | 80

    @Column(precision = 10, scale = 2)
    private BigDecimal weeklyPremium;

    private LocalDate weekStartDate;

    private LocalDate weekEndDate;

    private String status; // active | expired | cancelled

    @Column(length = 100)
    private String paymentReference;

    private LocalDateTime createdAt;

    // getters & setters

    public UUID getPlanId() {
        return planId;
    }

    public void setPlanId(UUID planId) {
        this.planId = planId;
    }

    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partnerId) {
        this.partnerId = partnerId;
    }

    public String getCoverageTier() {
        return coverageTier;
    }

    public void setCoverageTier(String coverageTier) {
        this.coverageTier = coverageTier;
    }

    public Integer getCoveragePercentage() {
        return coveragePercentage;
    }

    public void setCoveragePercentage(Integer coveragePercentage) {
        this.coveragePercentage = coveragePercentage;
    }

    public BigDecimal getWeeklyPremium() {
        return weeklyPremium;
    }

    public void setWeeklyPremium(BigDecimal weeklyPremium) {
        this.weeklyPremium = weeklyPremium;
    }

    public LocalDate getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(LocalDate weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public LocalDate getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(LocalDate weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}