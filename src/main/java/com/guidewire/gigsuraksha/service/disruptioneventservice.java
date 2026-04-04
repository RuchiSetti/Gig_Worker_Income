package com.guidewire.gigsuraksha.service;
import java.util.UUID;
public interface disruptioneventservice {
	

	

	

	  void detectTrigger(UUID zoneId);

	    void broadcastToPartners(UUID eventId);

	    void computeCRF(UUID eventId);

	    void closeEvent(UUID eventId);
}
