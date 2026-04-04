package com.guidewire.gigsuraksha.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID zoneId;

    @Column(length = 100, nullable = false)
    private String zoneName;

    @Column(length = 50)
    private String city;

    @Column(length = 50)
    private String state;

    @Column(length = 20)
    private String riskCategory; // low | medium | high | coastal

    @Column(precision = 4, scale = 3)
    private BigDecimal riskFactorR;

    @Column(precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(precision = 9, scale = 6)
    private BigDecimal longitude;

    private Boolean isFloodProne;

    private LocalDate lastRiskUpdated;

    // Getters & Setters
    public UUID getZoneId() {
        return zoneId;
    }

    public void setZoneId(UUID zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(String riskCategory) {
        this.riskCategory = riskCategory;
    }

    public BigDecimal getRiskFactorR() {
        return riskFactorR;
    }

    public void setRiskFactorR(BigDecimal riskFactorR) {
        this.riskFactorR = riskFactorR;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Boolean getIsFloodProne() {
        return isFloodProne;
    }

    public void setIsFloodProne(Boolean isFloodProne) {
        this.isFloodProne = isFloodProne;
    }

    public LocalDate getLastRiskUpdated() {
        return lastRiskUpdated;
    }

    public void setLastRiskUpdated(LocalDate lastRiskUpdated) {
        this.lastRiskUpdated = lastRiskUpdated;
    }
}