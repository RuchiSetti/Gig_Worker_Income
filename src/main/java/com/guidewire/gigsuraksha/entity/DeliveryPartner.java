package com.guidewire.gigsuraksha.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class DeliveryPartner {

    @Id
    @GeneratedValue
    private UUID partnerId;

    @Column(length = 100)
    private String fullName;

    @Column(length = 15, unique = true)
    private String mobileNumber;

    private String platform; // zomato | swiggy

    @Column(length = 50)
    private String city;

    private UUID zoneId;

    @Column(length = 50)
    private String upiId;

    @Column(precision = 4, scale = 1)
    private BigDecimal avgDailyHours;

    private LocalDateTime onboardedAt;

    private Boolean isActive;

    @Column(length = 128)
    private String firebaseUid;

	public UUID getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(UUID partnerId) {
		this.partnerId = partnerId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public UUID getZoneId() {
		return zoneId;
	}

	public void setZoneId(UUID zoneId) {
		this.zoneId = zoneId;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public BigDecimal getAvgDailyHours() {
		return avgDailyHours;
	}

	public void setAvgDailyHours(BigDecimal avgDailyHours) {
		this.avgDailyHours = avgDailyHours;
	}

	public LocalDateTime getOnboardedAt() {
		return onboardedAt;
	}

	public void setOnboardedAt(LocalDateTime onboardedAt) {
		this.onboardedAt = onboardedAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getFirebaseUid() {
		return firebaseUid;
	}

	public void setFirebaseUid(String firebaseUid) {
		this.firebaseUid = firebaseUid;
	}

    // getters & setters
}