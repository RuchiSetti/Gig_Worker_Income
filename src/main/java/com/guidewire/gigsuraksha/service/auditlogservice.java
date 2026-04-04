package com.guidewire.gigsuraksha.service;
import java.util.UUID;

public interface auditlogservice {
	

	

	  void logAction(UUID adminId, String action, String targetEntity, UUID targetId);

	    void getAdminHistory(UUID adminId);

	    void filterByEntity(String entity);
	}

