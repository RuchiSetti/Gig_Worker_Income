package com.guidewire.gigsuraksha.service;

import java.util.UUID;

import com.guidewire.gigsuraksha.entity.DeliveryPartner;

public interface deliverypartnerservice {


	    DeliveryPartner registerPartner(DeliveryPartner partner);

	    void verifyOTP(String mobileNumber, String otp);

	    DeliveryPartner updateProfile(UUID partnerId, DeliveryPartner partner);

	    void deactivateAccount(UUID partnerId);
	
}