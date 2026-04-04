package com.guidewire.gigsuraksha.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.guidewire.gigsuraksha.entity.DeliveryPartner;

import java.util.Optional;
import java.util.UUID;
public interface deliverypartnerrepository extends JpaRepository<DeliveryPartner, UUID>{
	 Optional<DeliveryPartner> findByMobileNumber(String mobileNumber);


	
}
