package com.guidewire.gigsuraksha.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class PremiumRequest {
    private UUID partnerId;
    private BigDecimal expectedWeeklyIncome;
    private String coverageTier; // basic | pro | max
    private String zone;

    public UUID getPartnerId() { return partnerId; }
    public void setPartnerId(UUID partnerId) { this.partnerId = partnerId; }
    public BigDecimal getExpectedWeeklyIncome() { return expectedWeeklyIncome; }
    public void setExpectedWeeklyIncome(BigDecimal expectedWeeklyIncome) { this.expectedWeeklyIncome = expectedWeeklyIncome; }
    public String getCoverageTier() { return coverageTier; }
    public void setCoverageTier(String coverageTier) { this.coverageTier = coverageTier; }
    public String getZone() { return zone; }
    public void setZone(String zone) { this.zone = zone; }
}
