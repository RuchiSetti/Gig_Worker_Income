package com.guidewire.gigsuraksha.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.InsurancePlan;

import java.util.Optional;
import java.util.UUID;

public interface insuranceplanrepository extends JpaRepository<InsurancePlan, UUID>{
	
	Optional<InsurancePlan> findByPartnerId(UUID partnerId);
}
