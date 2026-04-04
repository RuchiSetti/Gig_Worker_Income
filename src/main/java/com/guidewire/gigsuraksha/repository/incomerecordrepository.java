package com.guidewire.gigsuraksha.repository;


	import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.IncomeRecord;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

	public interface incomerecordrepository extends JpaRepository<IncomeRecord, UUID> {
		 Optional<IncomeRecord> findByPartnerIdAndRecordDate(UUID partnerId, LocalDate recordDate);
}
