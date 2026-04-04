package com.guidewire.gigsuraksha.repository;

	import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.Claim;

import java.util.Optional;
import java.util.UUID;

	public interface claimrepository extends JpaRepository<Claim, UUID> {
		 Optional<Claim> findByTriggerEventId(UUID triggerEventId);
}
