package com.guidewire.gigsuraksha.controller;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.premiumcalculationservice;

import java.time.LocalDate;
	import java.util.UUID;

	@RestController
	@RequestMapping("/api/premium")
	public class premiumcalculationcontroller {


	    @Autowired
	    private premiumcalculationservice service;

	    @PostMapping("/compute/{partnerId}")
	    public String computePremium(@PathVariable UUID partnerId,
	                                 @RequestParam String week,
	                                 @RequestParam Double expectedWeeklyIncome,
	                                 @RequestParam Double riskFactorR,
	                                 @RequestParam Double variabilityV,
	                                 @RequestParam Double coverageMultiplierC,
	                                 @RequestParam Double crf) {

	        service.computePremium(
	                partnerId,
	                LocalDate.parse(week),
	                expectedWeeklyIncome,
	                riskFactorR,
	                variabilityV,
	                coverageMultiplierC,
	                crf
	        );

	        return "Premium Computed";
	    }

	    @GetMapping("/breakdown/{calcId}")
	    public String getBreakdown(@PathVariable UUID calcId) {
	        service.getBreakdown(calcId);
	        return "Breakdown Retrieved";
	    }
	}