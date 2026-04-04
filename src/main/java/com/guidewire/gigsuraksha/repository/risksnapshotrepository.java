package com.guidewire.gigsuraksha.repository;

	import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.RiskSnapShot;

import java.util.UUID;

	public interface risksnapshotrepository extends JpaRepository<RiskSnapShot, UUID> {
	}

