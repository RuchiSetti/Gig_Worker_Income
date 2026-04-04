package com.guidewire.gigsuraksha.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class RiskSnapShot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID snapshotId;

    private UUID zoneId;

    private LocalDate weekStart;

    @Column(precision = 4, scale = 3)
    private BigDecimal weatherForecastScore;

    @Column(precision = 4, scale = 3)
    private BigDecimal seasonalRiskIndex;

    @Column(precision = 6, scale = 4)
    private BigDecimal historicalLossRatio;

    private Integer activePartners;

    @Column(precision = 12, scale = 2)
    private BigDecimal totalExposure;

    @Column(precision = 4, scale = 3)
    private BigDecimal computedCrf;

    private LocalDateTime createdAt;

    // Getters & Setters
    public UUID getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(UUID snapshotId) {
        this.snapshotId = snapshotId;
    }

    public UUID getZoneId() {
        return zoneId;
    }

    public void setZoneId(UUID zoneId) {
        this.zoneId = zoneId;
    }

    public LocalDate getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(LocalDate weekStart) {
        this.weekStart = weekStart;
    }

    public BigDecimal getWeatherForecastScore() {
        return weatherForecastScore;
    }

    public void setWeatherForecastScore(BigDecimal weatherForecastScore) {
        this.weatherForecastScore = weatherForecastScore;
    }

    public BigDecimal getSeasonalRiskIndex() {
        return seasonalRiskIndex;
    }

    public void setSeasonalRiskIndex(BigDecimal seasonalRiskIndex) {
        this.seasonalRiskIndex = seasonalRiskIndex;
    }

    public BigDecimal getHistoricalLossRatio() {
        return historicalLossRatio;
    }

    public void setHistoricalLossRatio(BigDecimal historicalLossRatio) {
        this.historicalLossRatio = historicalLossRatio;
    }

    public Integer getActivePartners() {
        return activePartners;
    }

    public void setActivePartners(Integer activePartners) {
        this.activePartners = activePartners;
    }

    public BigDecimal getTotalExposure() {
        return totalExposure;
    }

    public void setTotalExposure(BigDecimal totalExposure) {
        this.totalExposure = totalExposure;
    }

    public BigDecimal getComputedCrf() {
        return computedCrf;
    }

    public void setComputedCrf(BigDecimal computedCrf) {
        this.computedCrf = computedCrf;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}