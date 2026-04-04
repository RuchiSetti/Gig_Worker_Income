package com.guidewire.gigsuraksha.repository;


	import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.FraudFlag;

import java.util.UUID;

	public interface fraudflagrepository extends JpaRepository<FraudFlag, UUID> {
	
}
