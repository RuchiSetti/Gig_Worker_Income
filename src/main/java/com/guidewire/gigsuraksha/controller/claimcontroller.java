package com.guidewire.gigsuraksha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.claimservice;

import java.util.UUID;

@RestController
@RequestMapping("/api/claim/legacy")
public class claimcontroller {

    @Autowired
    private claimservice service;

    @PostMapping("/initiate/{triggerId}")
    public String initiateClaim(@PathVariable UUID triggerId,
                                @RequestParam UUID partnerId,
                                @RequestParam UUID planId) {
        service.initiateClaim(triggerId, partnerId, planId);
        return "Claim Initiated";
    }

    @PostMapping("/evaluate-loss/{claimId}")
    public String evaluateLoss(@PathVariable UUID claimId) {
        service.evaluateLoss(claimId);
        return "Loss Evaluated";
    }

    @PostMapping("/fraud-check/{claimId}")
    public String runFraudCheck(@PathVariable UUID claimId) {
        service.runFraudCheck(claimId);
        return "Fraud Check Done";
    }
}
