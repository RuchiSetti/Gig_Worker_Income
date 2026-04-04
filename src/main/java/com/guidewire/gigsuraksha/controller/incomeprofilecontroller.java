package com.guidewire.gigsuraksha.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.incomeprofileservice;

import java.time.LocalDate;
import java.util.UUID;



@RestController
@RequestMapping("/api/income-profile")
public class incomeprofilecontroller {

    @Autowired
    private incomeprofileservice service;

    @PostMapping("/build/{partnerId}")
    public String buildProfile(@PathVariable UUID partnerId) {
        service.buildProfile(partnerId);
        return "Profile Built";
    }

    @GetMapping("/predict/{partnerId}")
    public Double predictDailyIncome(@PathVariable UUID partnerId,
                                     @RequestParam String date) {
        return service.predictDailyIncome(partnerId, LocalDate.parse(date));
    }

    @PostMapping("/refresh/{partnerId}")
    public String refreshModel(@PathVariable UUID partnerId) {
        service.refreshModel(partnerId);
        return "Model Refreshed";
    }
}