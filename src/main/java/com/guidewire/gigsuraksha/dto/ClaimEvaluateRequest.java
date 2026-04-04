package com.guidewire.gigsuraksha.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ClaimEvaluateRequest {
    private UUID partnerId;
    private BigDecimal expectedIncome;
    private BigDecimal actualIncome;
    private String zone;
    private String deviceId;
    private BigDecimal gpsLat;
    private BigDecimal gpsLon;

    public UUID getPartnerId() { return partnerId; }
    public void setPartnerId(UUID partnerId) { this.partnerId = partnerId; }
    public BigDecimal getExpectedIncome() { return expectedIncome; }
    public void setExpectedIncome(BigDecimal expectedIncome) { this.expectedIncome = expectedIncome; }
    public BigDecimal getActualIncome() { return actualIncome; }
    public void setActualIncome(BigDecimal actualIncome) { this.actualIncome = actualIncome; }
    public String getZone() { return zone; }
    public void setZone(String zone) { this.zone = zone; }
    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
    public BigDecimal getGpsLat() { return gpsLat; }
    public void setGpsLat(BigDecimal gpsLat) { this.gpsLat = gpsLat; }
    public BigDecimal getGpsLon() { return gpsLon; }
    public void setGpsLon(BigDecimal gpsLon) { this.gpsLon = gpsLon; }
}
