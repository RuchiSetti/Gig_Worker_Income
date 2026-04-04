package com.guidewire.gigsuraksha.controller;



	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.entity.ManualTriggerOverride;
import com.guidewire.gigsuraksha.service.manualtriggeroverrideservice;

import java.util.UUID;

	@RestController
	@RequestMapping("/api/override")
	public class manualtriggeroverridecontroller {

		  @Autowired
		    private manualtriggeroverrideservice service;

		    @PostMapping("/create/{zoneId}")
		    public String createOverride(@PathVariable UUID zoneId,
		                                 @RequestParam String reason) {
		        service.createOverride(zoneId, reason);
		        return "Override Created";
		    }

		    @PostMapping("/expire/{overrideId}")
		    public String expireOverride(@PathVariable UUID overrideId) {
		        service.expireOverride(overrideId);
		        return "Override Expired";
		    }

		    @GetMapping("/active/{overrideId}")
		    public ManualTriggerOverride getActiveOverrides(@PathVariable UUID overrideId) {
		        return service.getActiveOverrides(overrideId);
		    }
		}
