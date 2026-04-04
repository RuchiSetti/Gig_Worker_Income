package com.guidewire.gigsuraksha.entity;



import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class ManualTriggerOverride {

    @Id
    @GeneratedValue
    private UUID overrideId;

    private UUID adminId;

    private UUID zoneId;

    private String reason; // curfew | strike | flood_alert | other

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime activeFrom;

    private LocalDateTime activeUntil;

    private UUID linkedEventId;

    // getters & setters

    public UUID getOverrideId() {
        return overrideId;
    }

    public void setOverrideId(UUID overrideId) {
        this.overrideId = overrideId;
    }

    public UUID getAdminId() {
        return adminId;
    }

    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }

    public UUID getZoneId() {
        return zoneId;
    }

    public void setZoneId(UUID zoneId) {
        this.zoneId = zoneId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(LocalDateTime activeFrom) {
        this.activeFrom = activeFrom;
    }

    public LocalDateTime getActiveUntil() {
        return activeUntil;
    }

    public void setActiveUntil(LocalDateTime activeUntil) {
        this.activeUntil = activeUntil;
    }

    public UUID getLinkedEventId() {
        return linkedEventId;
    }

    public void setLinkedEventId(UUID linkedEventId) {
        this.linkedEventId = linkedEventId;
    }
}
