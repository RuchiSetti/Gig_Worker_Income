package com.guidewire.gigsuraksha.controller;

	import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.zoneservice;

	@RestController
	@RequestMapping("/api/zone")
	public class zonecontroller {

		  @Autowired
		    private zoneservice service;

		    @GetMapping("/risk/{zoneId}")
		    public String getZoneRiskFactor(@PathVariable UUID zoneId) {
		        service.getZoneRiskFactor(zoneId);
		        return "Risk Factor Retrieved";
		    }

		    @PostMapping("/update/{zoneId}")
		    public String updateRiskScore(@PathVariable UUID zoneId) {
		        service.updateRiskScore(zoneId);
		        return "Risk Updated";
		    }

		    @GetMapping("/partners/{zoneId}")
		    public String getActivePartners(@PathVariable UUID zoneId) {
		        service.getActivePartners(zoneId);
		        return "Active Partners Retrieved";
		    }
		}
