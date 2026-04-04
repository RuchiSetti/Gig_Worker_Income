package com.guidewire.gigsuraksha.repository;


	import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.ManualTriggerOverride;

import java.util.UUID;

	public interface manualtriggeroverriderepository extends JpaRepository<ManualTriggerOverride, UUID> {
	}

