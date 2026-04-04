package com.guidewire.gigsuraksha.repository;

	import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.PremiumCalculation;

import java.util.UUID;

	public interface premiumcalculationrepository extends JpaRepository<PremiumCalculation, UUID> {
	
}
