package com.guidewire.gigsuraksha.repository;

	import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.PayOut;

import java.util.Optional;
import java.util.UUID;

	public interface payoutrepository extends JpaRepository<PayOut, UUID> {
		Optional<PayOut> findByClaimId(UUID claimId);

	}

