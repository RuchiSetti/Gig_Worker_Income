package com.guidewire.gigsuraksha.entity;



import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class RiskSnapShot {

    @Id
    @GeneratedValue
    private UUID snapshotId;

    private UUID zoneId;

    private LocalDate weekStart;

    @Column(precision = 4, scale = 3)
    private Double weatherForecastScore;

    @Column(precision = 4, scale = 3)
    private Double seasonalRiskIndex;

    @Column(precision = 6, scale = 4)
    private Double historicalLossRatio;

    private Integer activePartners;

    @Column(precision = 12, scale = 2)
    private Double totalExposure;

    @Column(precision = 4, scale = 3)
    private Double computedCrf;

    private LocalDateTime createdAt;

    // getters & setters

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

    public Double getWeatherForecastScore() {
        return weatherForecastScore;
    }

    public void setWeatherForecastScore(Double weatherForecastScore) {
        this.weatherForecastScore = weatherForecastScore;
    }

    public Double getSeasonalRiskIndex() {
        return seasonalRiskIndex;
    }

    public void setSeasonalRiskIndex(Double seasonalRiskIndex) {
        this.seasonalRiskIndex = seasonalRiskIndex;
    }

    public Double getHistoricalLossRatio() {
        return historicalLossRatio;
    }

    public void setHistoricalLossRatio(Double historicalLossRatio) {
        this.historicalLossRatio = historicalLossRatio;
    }

    public Integer getActivePartners() {
        return activePartners;
    }

    public void setActivePartners(Integer activePartners) {
        this.activePartners = activePartners;
    }

    public Double getTotalExposure() {
        return totalExposure;
    }

    public void setTotalExposure(Double totalExposure) {
        this.totalExposure = totalExposure;
    }

    public Double getComputedCrf() {
        return computedCrf;
    }

    public void setComputedCrf(Double computedCrf) {
        this.computedCrf = computedCrf;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}