package com.guidewire.gigsuraksha.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class PayOut {

    @Id
    @GeneratedValue
    private UUID payoutId;

    private UUID claimId;

    private UUID partnerId;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(length = 50)
    private String upiId;

    @Column(length = 100)
    private String razorpayReference;

    private String status; // initiated | success | failed

    private LocalDateTime initiatedAt;

    private LocalDateTime creditedAt;

    @Column(columnDefinition = "TEXT")
    private String failureReason;

    // getters & setters

    public UUID getPayoutId() {
        return payoutId;
    }

    public void setPayoutId(UUID payoutId) {
        this.payoutId = payoutId;
    }

    public UUID getClaimId() {
        return claimId;
    }

    public void setClaimId(UUID claimId) {
        this.claimId = claimId;
    }

    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partnerId) {
        this.partnerId = partnerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getRazorpayReference() {
        return razorpayReference;
    }

    public void setRazorpayReference(String razorpayReference) {
        this.razorpayReference = razorpayReference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getInitiatedAt() {
        return initiatedAt;
    }

    public void setInitiatedAt(LocalDateTime initiatedAt) {
        this.initiatedAt = initiatedAt;
    }

    public LocalDateTime getCreditedAt() {
        return creditedAt;
    }

    public void setCreditedAt(LocalDateTime creditedAt) {
        this.creditedAt = creditedAt;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}