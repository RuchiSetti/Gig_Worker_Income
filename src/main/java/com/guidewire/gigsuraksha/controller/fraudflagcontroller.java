package com.guidewire.gigsuraksha.controller;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.fraudflagservice;

import java.util.UUID;

	@RestController
	@RequestMapping("/api/fraud")
	public class fraudflagcontroller {

		   @Autowired
		    private fraudflagservice service;

		    @PostMapping("/rule-check/{claimId}")
		    public String runRuleChecks(@PathVariable UUID claimId) {
		        service.runRuleChecks(claimId);
		        return "Rule Check Completed";
		    }

		    @PostMapping("/xgboost/{flagId}")
		    public String scoreXGBoost(@PathVariable UUID flagId,
		                               @RequestParam String features) {
		        service.scoreXGBoost(flagId, features);
		        return "XGBoost Scored";
		    }

		    @PostMapping("/isolation/{flagId}")
		    public String runIsolationForest(@PathVariable UUID flagId) {
		        service.runIsolationForest(flagId);
		        return "Isolation Forest Run";
		    }

		    @PostMapping("/resolve/{flagId}/{adminId}")
		    public String resolveFlag(@PathVariable UUID flagId,
		                              @PathVariable UUID adminId) {
		        service.resolveFlag(flagId, adminId);
		        return "Flag Resolved";
		    }
		}

