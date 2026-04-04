package com.guidewire.gigsuraksha.controller;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.entity.SystemMetrics;
import com.guidewire.gigsuraksha.service.systemmetricsservice;

import java.time.LocalDate;
import java.util.List;

	@RestController
	@RequestMapping("/api/system-metrics")
	public class systemmetricecontroller {

		@Autowired
	    private systemmetricsservice service;

		@PostMapping("/compute")
	    public String computeDailyMetrics() {
	        service.computeDailyMetrics();
	        return "Metrics Computed";
	    }

	    // 🔥 get dashboard by date
	    @GetMapping("/dashboard")
	    public List<SystemMetrics> getLossRatioDashboard(@RequestParam String date) {
	        return service.getLossRatioDashboard(LocalDate.parse(date));
	    }

	    // 🔥 export report by date
	    @GetMapping("/export")
	    public List<SystemMetrics> exportReport(@RequestParam String period) {
	        return service.exportReport(LocalDate.parse(period));
	}
}