package com.guidewire.gigsuraksha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.trustscoreservice;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/trust-score")
public class trustscorecontroller {

    private final trustscoreservice service;

    @Autowired
    public trustscorecontroller(trustscoreservice service) {
        this.service = service;
    }

    // Apply a delta to a partner's trust score
    @PostMapping("/apply")
    public String applyDelta(@RequestParam UUID partnerId,
                             @RequestParam String reason,
                             @RequestParam Double amount) {
        service.applyDelta(partnerId, reason, amount);
        return "Delta Applied Successfully";
    }

    // Get the current trust score of a partner
    @GetMapping("/current")
    public BigDecimal getCurrentScore(@RequestParam UUID partnerId) {
        return service.getCurrentScore(partnerId); // new method in service returning BigDecimal
    }

    // Check if a partner should be suspended based on trust score
    @PostMapping("/check")
    public String checkSuspension(@RequestParam UUID partnerId) {
        service.checkSuspension(partnerId);
        return "Suspension Checked Successfully";
    }

    // Get the label of a partner based on trust score
    @GetMapping("/label")
    public String getLabel(@RequestParam UUID partnerId) {
        return service.getLabel(partnerId);
    }
}