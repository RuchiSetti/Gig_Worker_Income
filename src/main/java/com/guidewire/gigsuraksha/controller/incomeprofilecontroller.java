package com.guidewire.gigsuraksha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.incomeprofileservice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/income-profile")
public class incomeprofilecontroller {

    private final incomeprofileservice service;

    @Autowired
    public incomeprofilecontroller(incomeprofileservice service) {
        this.service = service;
    }

    // Build income profile for a partner
    @PostMapping("/build/{partnerId}")
    public String buildProfile(@PathVariable UUID partnerId) {
        service.buildProfile(partnerId);
        return "Profile Built Successfully";
    }

    // Predict daily income for a partner on a specific date
    @GetMapping("/predict/{partnerId}")
    public BigDecimal predictDailyIncome(@PathVariable UUID partnerId,
                                         @RequestParam String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return service.predictDailyIncome(partnerId, parsedDate); // returns BigDecimal
    }
    // Refresh the model for a partner
    @PostMapping("/refresh/{partnerId}")
    public String refreshModel(@PathVariable UUID partnerId) {
        service.refreshModel(partnerId);
        return "Model Refreshed Successfully";
    }
}