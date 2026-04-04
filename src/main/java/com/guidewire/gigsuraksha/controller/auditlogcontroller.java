package com.guidewire.gigsuraksha.controller;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.auditlogservice;

import java.util.UUID;

	@RestController
	@RequestMapping("/api/audit")
	public class auditlogcontroller {

		@Autowired
	    private auditlogservice service;

	    @PostMapping("/log/{adminId}")
	    public String logAction(@PathVariable UUID adminId,
	                            @RequestParam String action,
	                            @RequestParam String targetEntity,
	                            @RequestParam UUID targetId) {

	        service.logAction(adminId, action, targetEntity, targetId);
	        return "Action Logged";
	    }

	    @GetMapping("/history/{adminId}")
	    public String getAdminHistory(@PathVariable UUID adminId) {
	        service.getAdminHistory(adminId);
	        return "Admin History Retrieved";
	    }

	    @GetMapping("/filter")
	    public String filterByEntity(@RequestParam String entity) {
	        service.filterByEntity(entity);
	        return "Filtered Data Retrieved";
	    }
	}
