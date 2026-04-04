package com.guidewire.gigsuraksha.entity;



import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class AdminUser {

    @Id
    @GeneratedValue
    private UUID adminId;

    @Column(length = 150)
    private String email;

    private String role; // superadmin | ops | analyst | fraud_reviewer

    @Column(length = 100)
    private String fullName;

    private Boolean isActive;

    private LocalDateTime lastLogin;

    private LocalDateTime createdAt;

    // getters & setters

    public UUID getAdminId() {
        return adminId;
    }

    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}