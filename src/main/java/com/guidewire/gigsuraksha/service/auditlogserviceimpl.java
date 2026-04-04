package com.guidewire.gigsuraksha.service;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.AuditLog;
import com.guidewire.gigsuraksha.repository.auditlogrepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
	import java.util.UUID;

	@Service
	public class auditlogserviceimpl implements auditlogservice {
		@Autowired
	    private auditlogrepository repository;

	    @Override
	    public void logAction(UUID adminId, String action, String targetEntity, UUID targetId) {

	        AuditLog log = new AuditLog();

	        log.setAuditId(UUID.randomUUID());
	        log.setAdminId(adminId); // ✅ real admin
	        log.setAction(action);   // approve/reject/update
	        log.setTargetEntity(targetEntity); // claim / payout / fraud
	        log.setTargetId(targetId); // real entity id

	        log.setBeforeState("{}"); // later: JSON diff
	        log.setAfterState("{}");

	        log.setIpAddress("127.0.0.1");
	        log.setPerformedAt(LocalDateTime.now());

	        repository.save(log);
	    }

	    @Override
	    public void getAdminHistory(UUID adminId) {

	        List<AuditLog> logs = repository.findAll();

	        logs.stream()
	                .filter(log -> log.getAdminId().equals(adminId))
	                .forEach(log -> log.getAction());
	    }

	    @Override
	    public void filterByEntity(String entity) {

	        List<AuditLog> logs = repository.findAll();

	        logs.stream()
	                .filter(log -> log.getTargetEntity().equalsIgnoreCase(entity))
	                .forEach(log -> log.getAction());
	    }
	}