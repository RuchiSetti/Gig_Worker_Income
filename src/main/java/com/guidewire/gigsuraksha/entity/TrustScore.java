package com.guidewire.gigsuraksha.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TrustScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID trustId;

    @Column(unique = true, nullable = false)
    private UUID partnerId;

    @Column(precision = 4, scale = 3)
    private BigDecimal currentScore;

    private String label; // excellent | good | average | risk

    private Integer cleanClaimsCount;

    private Integer fraudFlagsCount;

    private Boolean isSuspended;

    @Column(length = 100)
    private String lastEvent;

    private LocalDateTime updatedAt;

    // Getters & Setters
    public UUID getTrustId() {
        return trustId;
    }

    public void setTrustId(UUID trustId) {
        this.trustId = trustId;
    }

    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partnerId) {
        this.partnerId = partnerId;
    }

    public BigDecimal getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(BigDecimal currentScore) {
        this.currentScore = currentScore;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getCleanClaimsCount() {
        return cleanClaimsCount;
    }

    public void setCleanClaimsCount(Integer cleanClaimsCount) {
        this.cleanClaimsCount = cleanClaimsCount;
    }

    public Integer getFraudFlagsCount() {
        return fraudFlagsCount;
    }

    public void setFraudFlagsCount(Integer fraudFlagsCount) {
        this.fraudFlagsCount = fraudFlagsCount;
    }

    public Boolean getIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(Boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    public String getLastEvent() {
        return lastEvent;
    }

    public void setLastEvent(String lastEvent) {
        this.lastEvent = lastEvent;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}