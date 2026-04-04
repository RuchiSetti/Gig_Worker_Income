package com.guidewire.gigsuraksha.entity;



import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Zone {

    @Id
    @GeneratedValue
    private UUID zoneId;

    @Column(length = 100)
    private String zoneName;

    @Column(length = 50)
    private String city;

    @Column(length = 50)
    private String state;

    private String riskCategory; // low | medium | high | coastal

    @Column(precision = 4, scale = 3)
    private Double riskFactorR;

    @Column(precision = 9, scale = 6)
    private Double latitude;

    @Column(precision = 9, scale = 6)
    private Double longitude;

    private Boolean isFloodProne;

    private LocalDate lastRiskUpdated;

    // getters & setters

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

    public Double getRiskFactorR() {
        return riskFactorR;
    }

    public void setRiskFactorR(Double riskFactorR) {
        this.riskFactorR = riskFactorR;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean getIsFloodProne() {
        return isFloodProne;
    }

    public void setIsFloodProne(Boolean floodProne) {
        isFloodProne = floodProne;
    }

    public LocalDate getLastRiskUpdated() {
        return lastRiskUpdated;
    }

    public void setLastRiskUpdated(LocalDate lastRiskUpdated) {
        this.lastRiskUpdated = lastRiskUpdated;
    }
}