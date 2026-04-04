package com.guidewire.gigsuraksha.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue
    private UUID auditId;

    private UUID adminId;

    @Column(length = 100)
    private String action;

    @Column(length = 50)
    private String targetEntity;

    private UUID targetId;

    @Column(columnDefinition = "JSONB")
    private String beforeState;

    @Column(columnDefinition = "JSONB")
    private String afterState;

    @Column(length = 45)
    private String ipAddress;

    private LocalDateTime performedAt;

    // getters & setters

    public UUID getAuditId() {
        return auditId;
    }

    public void setAuditId(UUID auditId) {
        this.auditId = auditId;
    }

    public UUID getAdminId() {
        return adminId;
    }

    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(String targetEntity) {
        this.targetEntity = targetEntity;
    }

    public UUID getTargetId() {
        return targetId;
    }

    public void setTargetId(UUID targetId) {
        this.targetId = targetId;
    }

    public String getBeforeState() {
        return beforeState;
    }

    public void setBeforeState(String beforeState) {
        this.beforeState = beforeState;
    }

    public String getAfterState() {
        return afterState;
    }

    public void setAfterState(String afterState) {
        this.afterState = afterState;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getPerformedAt() {
        return performedAt;
    }

    public void setPerformedAt(LocalDateTime performedAt) {
        this.performedAt = performedAt;
    }
}
