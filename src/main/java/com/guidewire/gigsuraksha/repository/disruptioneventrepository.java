package com.guidewire.gigsuraksha.repository;


	import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.DisruptionEvent;

import java.util.UUID;

	public interface disruptioneventrepository extends JpaRepository<DisruptionEvent, UUID> {
	
}
