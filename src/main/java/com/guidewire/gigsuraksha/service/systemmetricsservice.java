package com.guidewire.gigsuraksha.service;
import java.time.LocalDate;
import java.util.List;

import com.guidewire.gigsuraksha.entity.SystemMetrics;
public interface systemmetricsservice {
	
	 void computeDailyMetrics();

	 List<SystemMetrics> getLossRatioDashboard(LocalDate date);

	    List<SystemMetrics> exportReport(LocalDate period);
	}