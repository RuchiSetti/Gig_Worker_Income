package com.guidewire.gigsuraksha.service;

import java.util.UUID;

public interface adminuserservice {
	 void login(UUID adminId);

	    void assignRole(UUID adminId, String role);

	    void revokeAccess(UUID adminId);
	}
