package com.guidewire.gigsuraksha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.zoneservice;

import java.util.UUID;

@RestController
@RequestMapping("/api/zone")
public class zonecontroller {

    private final zoneservice service;

    @Autowired
    public zonecontroller(zoneservice service) {
        this.service = service;
    }

    // Retrieve the risk factor of a zone
    @GetMapping("/risk/{zoneId}")
    public String getZoneRiskFactor(@PathVariable UUID zoneId) {
        service.getZoneRiskFactor(zoneId);
        return "Risk Factor Retrieved";
    }

    // Update the risk score of a zone
    @PostMapping("/update/{zoneId}")
    public String updateRiskScore(@PathVariable UUID zoneId,
                                  @RequestParam double newRiskScore) {
        service.updateRiskScore(zoneId, newRiskScore);
        return "Risk Score Updated Successfully";
    }
    // Retrieve active partners for a zone
    @GetMapping("/partners/{zoneId}")
    public String getActivePartners(@PathVariable UUID zoneId) {
        service.getActivePartners(zoneId);
        return "Active Partners Retrieved";
    }
}