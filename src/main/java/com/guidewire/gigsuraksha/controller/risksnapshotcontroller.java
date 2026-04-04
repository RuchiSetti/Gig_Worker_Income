package com.guidewire.gigsuraksha.controller;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.risksnapshotservice;

import java.util.UUID;

	@RestController
	@RequestMapping("/api/risk-snapshot")
	public class risksnapshotcontroller {

		  @Autowired
		    private risksnapshotservice service;

		    @PostMapping("/generate/{zoneId}")
		    public String generateWeeklySnapshot(@PathVariable UUID zoneId) {
		        service.generateWeeklySnapshot(zoneId);
		        return "Snapshot Generated";
		    }

		    @GetMapping("/exposure/{zoneId}")
		    public String getZoneExposure(@PathVariable UUID zoneId) {
		        service.getZoneExposure(zoneId);
		        return "Zone Exposure Retrieved";
		    }

		    @PostMapping("/flag/{snapshotId}")
		    public String flagHighRiskZones(@PathVariable UUID snapshotId) {
		        service.flagHighRiskZones(snapshotId);
		        return "High Risk Zones Flagged";
		    }
		}
