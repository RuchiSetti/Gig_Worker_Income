package com.guidewire.gigsuraksha.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.incomeprofileservice;
import com.guidewire.gigsuraksha.service.insuranceplanservice;

import java.time.LocalDate;
import java.util.UUID;
@RestController
@RequestMapping("/api/insurance")
public class insuranceplancontroller {

    @Autowired
    private insuranceplanservice service;

    // ✅ takes partnerId
    @PostMapping("/calculate/{partnerId}")
    public String calculatePremium(@PathVariable UUID partnerId) {
        service.calculatePremium(partnerId);
        return "Premium Calculated";
    }

    // ✅ takes planId
    @PostMapping("/activate/{planId}")
    public String activatePlan(@PathVariable UUID planId) {
        service.activatePlan(planId);
        return "Plan Activated";
    }

    @PostMapping("/renew/{planId}")
    public String renewWeekly(@PathVariable UUID planId) {
        service.renewWeekly(planId);
        return "Plan Renewed";
    }

    @DeleteMapping("/cancel/{planId}")
    public String cancelPlan(@PathVariable UUID planId) {
        service.cancelPlan(planId);
        return "Plan Cancelled";
    }
}
