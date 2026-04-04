package com.guidewire.gigsuraksha.repository;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.IncomeProfile;



public interface incomeprofilerepository extends JpaRepository<IncomeProfile, UUID>{
	

	 Optional<IncomeProfile> findByPartnerId(UUID partnerId); 

	}
	

