package com.guidewire.gigsuraksha.controller;

	import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.adminuserservice;

	@RestController
	@RequestMapping("/api/admin")
	public class adminusercontroller {

		 @Autowired
		    private adminuserservice service;

		    @PostMapping("/login/{adminId}")
		    public String login(@PathVariable UUID adminId) {
		        service.login(adminId);
		        return "Admin Logged In";
		    }

		    @PostMapping("/assign-role/{adminId}")
		    public String assignRole(@PathVariable UUID adminId,
		                             @RequestParam String role) {
		        service.assignRole(adminId, role);
		        return "Role Assigned";
		    }

		    @PostMapping("/revoke/{adminId}")
		    public String revokeAccess(@PathVariable UUID adminId) {
		        service.revokeAccess(adminId);
		        return "Access Revoked";
		    }
		}