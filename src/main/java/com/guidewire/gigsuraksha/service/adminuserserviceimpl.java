package com.guidewire.gigsuraksha.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.AdminUser;
import com.guidewire.gigsuraksha.repository.adminuserrepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class adminuserserviceimpl implements adminuserservice {

	  @Autowired
	    private adminuserrepository repository;

	    @Override
	    public void login(UUID adminId) {

	        Optional<AdminUser> optional = repository.findById(adminId);

	        if (optional.isPresent()) {
	            AdminUser admin = optional.get();
	            admin.setLastLogin(LocalDateTime.now());
	            repository.save(admin);
	        }
	    }

	    @Override
	    public void assignRole(UUID adminId, String role) {

	        Optional<AdminUser> optional = repository.findById(adminId);

	        if (optional.isPresent()) {
	            AdminUser admin = optional.get();
	            admin.setRole(role);
	            repository.save(admin);
	        }
	    }

	    @Override
	    public void revokeAccess(UUID adminId) {

	        Optional<AdminUser> optional = repository.findById(adminId);

	        if (optional.isPresent()) {
	            AdminUser admin = optional.get();
	            admin.setIsActive(false);
	            repository.save(admin);
	        }
	    }
	}