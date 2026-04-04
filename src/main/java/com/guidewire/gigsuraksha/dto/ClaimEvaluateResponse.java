package com.guidewire.gigsuraksha.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ClaimEvaluateResponse {
    private UUID claimId;
    private String status;
    private BigDecimal expectedIncome;
    private BigDecimal actualIncome;
    private BigDecimal loss;
    private BigDecimal zScore;
    private BigDecimal coveragePercent;
    private BigDecimal trustScore;
    private BigDecimal finalPayout;
    private String fraudDecision;
    private String rejectionReason;
    private String formula;

    public UUID getClaimId() { return claimId; }
    public void setClaimId(UUID claimId) { this.claimId = claimId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getExpectedIncome() { return expectedIncome; }
    public void setExpectedIncome(BigDecimal expectedIncome) { this.expectedIncome = expectedIncome; }
    public BigDecimal getActualIncome() { return actualIncome; }
    public void setActualIncome(BigDecimal actualIncome) { this.actualIncome = actualIncome; }
    public BigDecimal getLoss() { return loss; }
    public void setLoss(BigDecimal loss) { this.loss = loss; }
    public BigDecimal getzScore() { return zScore; }
    public void setzScore(BigDecimal zScore) { this.zScore = zScore; }
    public BigDecimal getCoveragePercent() { return coveragePercent; }
    public void setCoveragePercent(BigDecimal coveragePercent) { this.coveragePercent = coveragePercent; }
    public BigDecimal getTrustScore() { return trustScore; }
    public void setTrustScore(BigDecimal trustScore) { this.trustScore = trustScore; }
    public BigDecimal getFinalPayout() { return finalPayout; }
    public void setFinalPayout(BigDecimal finalPayout) { this.finalPayout = finalPayout; }
    public String getFraudDecision() { return fraudDecision; }
    public void setFraudDecision(String fraudDecision) { this.fraudDecision = fraudDecision; }
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
    public String getFormula() { return formula; }
    public void setFormula(String formula) { this.formula = formula; }
}
