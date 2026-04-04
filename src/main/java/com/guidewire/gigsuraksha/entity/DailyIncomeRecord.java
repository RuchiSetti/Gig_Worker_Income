package com.guidewire.gigsuraksha.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class DailyIncomeRecord {

    @Id
    @GeneratedValue
    private UUID recordId;

    private UUID partnerId;

    private LocalDate recordDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal actualIncome;

    @Column(precision = 10, scale = 2)
    private BigDecimal expectedIncome;

    @Column(precision = 10, scale = 2)
    private BigDecimal incomeLoss;

    @Column(precision = 6, scale = 4)
    private BigDecimal zScore;

    private Integer ordersCompleted;

    @Column(precision = 4, scale = 2)
    private BigDecimal hoursActive;

    private String dataSource; // platform_api | simulated

    // getters & setters

    public UUID getRecordId() {
        return recordId;
    }

    public void setRecordId(UUID recordId) {
        this.recordId = recordId;
    }

    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partnerId) {
        this.partnerId = partnerId;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public BigDecimal getActualIncome() {
        return actualIncome;
    }

    public void setActualIncome(BigDecimal actualIncome) {
        this.actualIncome = actualIncome;
    }

    public BigDecimal getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(BigDecimal expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public BigDecimal getIncomeLoss() {
        return incomeLoss;
    }

    public void setIncomeLoss(BigDecimal incomeLoss) {
        this.incomeLoss = incomeLoss;
    }

    public BigDecimal getZScore() {
        return zScore;
    }

    public void setZScore(BigDecimal zScore) {
        this.zScore = zScore;
    }

    public Integer getOrdersCompleted() {
        return ordersCompleted;
    }

    public void setOrdersCompleted(Integer ordersCompleted) {
        this.ordersCompleted = ordersCompleted;
    }

    public BigDecimal getHoursActive() {
        return hoursActive;
    }

    public void setHoursActive(BigDecimal hoursActive) {
        this.hoursActive = hoursActive;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}