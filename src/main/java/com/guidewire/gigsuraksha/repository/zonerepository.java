package com.guidewire.gigsuraksha.repository;


	import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.Zone;

import java.util.UUID;

	public interface zonerepository extends JpaRepository<Zone, UUID> {
	
}
