package com.guidewire.gigsuraksha.repository;

	import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.TrustScore;

import java.util.Optional;
import java.util.UUID;

	public interface trustscorerepository extends JpaRepository<TrustScore, UUID> {
		Optional<TrustScore> findByPartnerId(UUID partnerId);
}
