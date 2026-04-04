package com.guidewire.gigsuraksha.repository; 
	import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.AuditLog;

	public interface auditlogrepository extends JpaRepository<AuditLog, UUID> {
	
}
