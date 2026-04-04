package com.guidewire.gigsuraksha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.DeliveryPartner;
import com.guidewire.gigsuraksha.repository.deliverypartnerrepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class deliverypartnerserviceimpl implements deliverypartnerservice {

    @Autowired
    private deliverypartnerrepository repository;

    @Override
    public DeliveryPartner registerPartner(DeliveryPartner partner) {

        partner.setOnboardedAt(LocalDateTime.now());
        partner.setIsActive(true);

        return repository.save(partner);
    }

    @Override
    public void verifyOTP(String mobileNumber, String otp) {

        Optional<DeliveryPartner> optional = repository.findByMobileNumber(mobileNumber);

        if (optional.isPresent()) {
            System.out.println("OTP Verified for " + mobileNumber);
        }
    }

    @Override
    public DeliveryPartner updateProfile(UUID partnerId, DeliveryPartner updated) {

        Optional<DeliveryPartner> optional = repository.findById(partnerId);

        if (optional.isPresent()) {
            DeliveryPartner partner = optional.get();

            partner.setFullName(updated.getFullName());
            partner.setCity(updated.getCity());
            partner.setPlatform(updated.getPlatform());
            partner.setUpiId(updated.getUpiId());
            partner.setAvgDailyHours(updated.getAvgDailyHours());

            return repository.save(partner);
        }

        return null;
    }

    @Override
    public void deactivateAccount(UUID partnerId) {

        Optional<DeliveryPartner> optional = repository.findById(partnerId);

        if (optional.isPresent()) {
            DeliveryPartner partner = optional.get();
            partner.setIsActive(false);
            repository.save(partner);
        }
    }
}