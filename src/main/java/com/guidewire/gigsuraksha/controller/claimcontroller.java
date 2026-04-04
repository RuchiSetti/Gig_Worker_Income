package com.guidewire.gigsuraksha.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.claimservice;

import java.util.UUID;

@RestController
@RequestMapping("/api/claim")
public class claimcontroller {

    @Autowired
    private claimservice service;

    // ✅ takes triggerId + partnerId + planId
    @PostMapping("/initiate/{triggerId}")
    public String initiateClaim(@PathVariable UUID triggerId,
                                @RequestParam UUID partnerId,
                                @RequestParam UUID planId) {
        service.initiateClaim(triggerId, partnerId, planId);
        return "Claim Initiated";
    }

    // ✅ takes claimId
    @PostMapping("/evaluate/{claimId}")
    public String evaluateLoss(@PathVariable UUID claimId) {
        service.evaluateLoss(claimId);
        return "Loss Evaluated";
    }

    @PostMapping("/fraud-check/{claimId}")
    public String runFraudCheck(@PathVariable UUID claimId) {
        service.runFraudCheck(claimId);
        return "Fraud Check Done";
    }

    @PostMapping("/approve/{claimId}")
    public String approveClaim(@PathVariable UUID claimId) {
        service.approveClaim(claimId);
        return "Claim Approved";
    }

    @PostMapping("/reject/{claimId}")
    public String rejectClaim(@PathVariable UUID claimId,
                              @RequestParam String reason) {
        service.rejectClaim(claimId, reason);
        return "Claim Rejected";
    }
}
