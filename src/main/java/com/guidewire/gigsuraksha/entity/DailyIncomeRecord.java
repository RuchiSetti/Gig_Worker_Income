package com.guidewire.gigsuraksha.entity;

import jakarta.persistence.*;
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
    private Double actualIncome;

    @Column(precision = 10, scale = 2)
    private Double expectedIncome;

    @Column(precision = 10, scale = 2)
    private Double incomeLoss;

    @Column(precision = 6, scale = 4)
    private Double zScore;

    private Integer ordersCompleted;

    @Column(precision = 4, scale = 2)
    private Double hoursActive;

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

    public Double getActualIncome() {
        return actualIncome;
    }

    public void setActualIncome(Double actualIncome) {
        this.actualIncome = actualIncome;
    }

    public Double getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(Double expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public Double getIncomeLoss() {
        return incomeLoss;
    }

    public void setIncomeLoss(Double incomeLoss) {
        this.incomeLoss = incomeLoss;
    }

    public Double getZScore() {
        return zScore;
    }

    public void setZScore(Double zScore) {
        this.zScore = zScore;
    }

    public Integer getOrdersCompleted() {
        return ordersCompleted;
    }

    public void setOrdersCompleted(Integer ordersCompleted) {
        this.ordersCompleted = ordersCompleted;
    }

    public Double getHoursActive() {
        return hoursActive;
    }

    public void setHoursActive(Double hoursActive) {
        this.hoursActive = hoursActive;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}
