package com.guidewire.gigsuraksha.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TrustScoreLog {

    @Id
    @GeneratedValue
    private UUID logId;

    private UUID partnerId;

    private String eventType; // clean_claim | gps_spoof | fraud | duplicate...

    @Column(precision = 4, scale = 3)
    private Double delta;

    @Column(precision = 4, scale = 3)
    private Double scoreBefore;

    @Column(precision = 4, scale = 3)
    private Double scoreAfter;

    private UUID relatedClaimId;

    private LocalDateTime loggedAt;

    // getters & setters

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

    public Double getDelta() {
        return delta;
    }

    public void setDelta(Double delta) {
        this.delta = delta;
    }

    public Double getScoreBefore() {
        return scoreBefore;
    }

    public void setScoreBefore(Double scoreBefore) {
        this.scoreBefore = scoreBefore;
    }

    public Double getScoreAfter() {
        return scoreAfter;
    }

    public void setScoreAfter(Double scoreAfter) {
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
