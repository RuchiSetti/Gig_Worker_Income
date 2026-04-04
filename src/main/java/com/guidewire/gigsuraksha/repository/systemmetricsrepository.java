package com.guidewire.gigsuraksha.repository;


	import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.SystemMetrics;

import java.util.UUID;

	public interface systemmetricsrepository extends JpaRepository<SystemMetrics, UUID> {
	}

