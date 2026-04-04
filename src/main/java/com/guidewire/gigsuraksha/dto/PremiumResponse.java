package com.guidewire.gigsuraksha.dto;

import java.math.BigDecimal;

public class PremiumResponse {
    private String coverageTier;
    private Integer coveragePercent;
    private BigDecimal weeklyPremium;
    private BigDecimal kFactor;
    private BigDecimal riskFactor;
    private BigDecimal variability;
    private BigDecimal coverageMultiplier;
    private BigDecimal crf;
    private BigDecimal expectedWeeklyIncome;
    private String formula;

    public String getCoverageTier() { return coverageTier; }
    public void setCoverageTier(String coverageTier) { this.coverageTier = coverageTier; }
    public Integer getCoveragePercent() { return coveragePercent; }
    public void setCoveragePercent(Integer coveragePercent) { this.coveragePercent = coveragePercent; }
    public BigDecimal getWeeklyPremium() { return weeklyPremium; }
    public void setWeeklyPremium(BigDecimal weeklyPremium) { this.weeklyPremium = weeklyPremium; }
    public BigDecimal getkFactor() { return kFactor; }
    public void setkFactor(BigDecimal kFactor) { this.kFactor = kFactor; }
    public BigDecimal getRiskFactor() { return riskFactor; }
    public void setRiskFactor(BigDecimal riskFactor) { this.riskFactor = riskFactor; }
    public BigDecimal getVariability() { return variability; }
    public void setVariability(BigDecimal variability) { this.variability = variability; }
    public BigDecimal getCoverageMultiplier() { return coverageMultiplier; }
    public void setCoverageMultiplier(BigDecimal coverageMultiplier) { this.coverageMultiplier = coverageMultiplier; }
    public BigDecimal getCrf() { return crf; }
    public void setCrf(BigDecimal crf) { this.crf = crf; }
    public BigDecimal getExpectedWeeklyIncome() { return expectedWeeklyIncome; }
    public void setExpectedWeeklyIncome(BigDecimal expectedWeeklyIncome) { this.expectedWeeklyIncome = expectedWeeklyIncome; }
    public String getFormula() { return formula; }
    public void setFormula(String formula) { this.formula = formula; }
}
