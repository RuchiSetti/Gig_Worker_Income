package com.guidewire.gigsuraksha.controller;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.disruptioneventservice;

import java.util.UUID;

	@RestController
	@RequestMapping("/api/disruption")
	public class disruptioneventcontroller {

		  @Autowired
		    private disruptioneventservice service;

		    @PostMapping("/detect/{zoneId}")
		    public String detectTrigger(@PathVariable UUID zoneId) {
		        service.detectTrigger(zoneId);
		        return "Trigger Detected";
		    }

		    @PostMapping("/broadcast/{eventId}")
		    public String broadcastToPartners(@PathVariable UUID eventId) {
		        service.broadcastToPartners(eventId);
		        return "Broadcast Done";
		    }

		    @PostMapping("/compute/{eventId}")
		    public String computeCRF(@PathVariable UUID eventId) {
		        service.computeCRF(eventId);
		        return "CRF Computed";
		    }

		    @PostMapping("/close/{eventId}")
		    public String closeEvent(@PathVariable UUID eventId) {
		        service.closeEvent(eventId);
		        return "Event Closed";
		    }
		}
