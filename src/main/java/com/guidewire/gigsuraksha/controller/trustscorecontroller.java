package com.guidewire.gigsuraksha.controller;

	import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.trustscoreservice;

	@RestController
	@RequestMapping("/api/trust-score")
	public class trustscorecontroller {

		@Autowired
	    private trustscoreservice service;

	    @PostMapping("/apply")
	    public String applyDelta(@RequestParam UUID partnerId,
	                             @RequestParam String reason,
	                             @RequestParam Double amount) {
	        service.applyDelta(partnerId, reason, amount);
	        return "Delta Applied";
	    }

	    @GetMapping("/current")
	    public Double getCurrentScore(@RequestParam UUID partnerId) {
	        return service.getCurrentScore(partnerId);
	    }

	    @PostMapping("/check")
	    public String checkSuspension(@RequestParam UUID partnerId) {
	        service.checkSuspension(partnerId);
	        return "Suspension Checked";
	    }

	    @GetMapping("/label")
	    public String getLabel(@RequestParam UUID partnerId) {
	        return service.getLabel(partnerId);
	    }
	}
