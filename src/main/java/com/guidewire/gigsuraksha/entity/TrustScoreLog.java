package com.guidewire.gigsuraksha.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TrustScoreLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID logId;

    @Column(nullable = false)
    private UUID partnerId;

    private String eventType; // clean_claim | gps_spoof | fraud | duplicate | other

    @Column(precision = 4, scale = 3)
    private BigDecimal delta;

    @Column(precision = 4, scale = 3)
    private BigDecimal scoreBefore;

    @Column(precision = 4, scale = 3)
    private BigDecimal scoreAfter;

    private UUID relatedClaimId;

    private LocalDateTime loggedAt;

    // Getters & Setters
    public UUID getLogId() {
        return logId;
    }

    public void setLogId(UUID logId) {
        this.logId = logId;
    }

    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partnerId) {
        this.partnerId = partnerId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public BigDecimal getDelta() {
        return delta;
    }

    public void setDelta(BigDecimal delta) {
        this.delta = delta;
    }

    public BigDecimal getScoreBefore() {
        return scoreBefore;
    }

    public void setScoreBefore(BigDecimal scoreBefore) {
        this.scoreBefore = scoreBefore;
    }

    public BigDecimal getScoreAfter() {
        return scoreAfter;
    }

    public void setScoreAfter(BigDecimal scoreAfter) {
        this.scoreAfter = scoreAfter;
    }

    public UUID getRelatedClaimId() {
        return relatedClaimId;
    }

    public void setRelatedClaimId(UUID relatedClaimId) {
        this.relatedClaimId = relatedClaimId;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }
}