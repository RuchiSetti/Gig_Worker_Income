package com.guidewire.gigsuraksha.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class DisruptionEvent {

    @Id
    @GeneratedValue
    private UUID eventId;

    private UUID zoneId;

    private String eventType; // heavy_rain | extreme_heat | severe_aqi | flood | shutdown

    @Column(precision = 10, scale = 2)
    private BigDecimal triggerValue;

    @Column(precision = 10, scale = 2)
    private BigDecimal thresholdValue;

    @Column(length = 50)
    private String dataSource;

    private String severity; // low | medium | high | critical

    private LocalDateTime eventStart;

    private LocalDateTime eventEnd;

    @Column(precision = 4, scale = 3)
    private BigDecimal crfValue;

    private Integer affectedPartnersCount;

    // getters & setters

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getZoneId() {
        return zoneId;
    }

    public void setZoneId(UUID zoneId) {
        this.zoneId = zoneId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public BigDecimal getTriggerValue() {
        return triggerValue;
    }

    public void setTriggerValue(BigDecimal triggerValue) {
        this.triggerValue = triggerValue;
    }

    public BigDecimal getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(BigDecimal thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public LocalDateTime getEventStart() {
        return eventStart;
    }

    public void setEventStart(LocalDateTime eventStart) {
        this.eventStart = eventStart;
    }

    public LocalDateTime getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(LocalDateTime eventEnd) {
        this.eventEnd = eventEnd;
    }

    public BigDecimal getCrfValue() {
        return crfValue;
    }

    public void setCrfValue(BigDecimal crfValue) {
        this.crfValue = crfValue;
    }

    public Integer getAffectedPartnersCount() {
        return affectedPartnersCount;
    }

    public void setAffectedPartnersCount(Integer affectedPartnersCount) {
        this.affectedPartnersCount = affectedPartnersCount;
    }
}