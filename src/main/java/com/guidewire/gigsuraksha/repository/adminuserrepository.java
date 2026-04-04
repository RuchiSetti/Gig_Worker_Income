package com.guidewire.gigsuraksha.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.AdminUser;

import java.util.UUID;

public interface adminuserrepository extends JpaRepository<AdminUser, UUID> {
}
