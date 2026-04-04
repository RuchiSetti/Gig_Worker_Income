package com.guidewire.gigsuraksha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.entity.DeliveryPartner;
import com.guidewire.gigsuraksha.service.deliverypartnerservice;

import java.util.UUID;

@RestController
@RequestMapping("/api/partners")
public class deliverypartnercontroller {

    @Autowired
    private deliverypartnerservice service;

    // ✅ takes full data from frontend
    @PostMapping("/register")
    public DeliveryPartner registerPartner(@RequestBody DeliveryPartner partner) {
        return service.registerPartner(partner);
    }

    // ✅ takes mobile + otp
    @PostMapping("/verify-otp")
    public String verifyOTP(@RequestParam String mobile,
                            @RequestParam String otp) {
        service.verifyOTP(mobile, otp);
        return "OTP Verified";
    }

    // ✅ takes id + updated data
    @PutMapping("/update/{id}")
    public DeliveryPartner updateProfile(@PathVariable UUID id,
                                         @RequestBody DeliveryPartner partner) {
        return service.updateProfile(id, partner);
    }

    // ✅ takes id
    @DeleteMapping("/deactivate/{id}")
    public String deactivateAccount(@PathVariable UUID id) {
        service.deactivateAccount(id);
        return "Account Deactivated";
    }
}