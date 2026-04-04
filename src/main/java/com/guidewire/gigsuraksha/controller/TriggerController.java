package com.guidewire.gigsuraksha.controller;

import com.guidewire.gigsuraksha.entity.DisruptionEvent;
import com.guidewire.gigsuraksha.repository.disruptioneventrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/triggers")
public class TriggerController {

    @Autowired
    private disruptioneventrepository eventRepo;

    @GetMapping("/weather")
    public ResponseEntity<?> getWeather(@RequestParam(defaultValue = "Koramangala") String zone) {
        // Mock weather data simulating real sensor feed
        Map<String, Object> data = new HashMap<>();
        data.put("type", "weather");
        data.put("zone", zone);
        data.put("rainfall_mm", 88.5);
        data.put("severity", "high");
        data.put("threshold_mm", 50.0);
        data.put("triggered", true);
        data.put("timestamp", LocalDateTime.now().toString());
        data.put("message", "Heavy rainfall detected. Income disruption likely.");
        return ResponseEntity.ok(data);
    }

    @GetMapping("/aqi")
    public ResponseEntity<?> getAqi(@RequestParam(defaultValue = "Koramangala") String zone) {
        Map<String, Object> data = new HashMap<>();
        data.put("type", "aqi");
        data.put("zone", zone);
        data.put("aqi_value", 185);
        data.put("severity", "medium");
        data.put("threshold_aqi", 150);
        data.put("triggered", true);
        data.put("timestamp", LocalDateTime.now().toString());
        data.put("message", "Poor air quality. Rider activity may be reduced.");
        return ResponseEntity.ok(data);
    }

    @GetMapping("/traffic")
    public ResponseEntity<?> getTraffic(@RequestParam(defaultValue = "Koramangala") String zone) {
        Map<String, Object> data = new HashMap<>();
        data.put("type", "traffic");
        data.put("zone", zone);
        data.put("congestion_index", 0.72);
        data.put("severity", "medium");
        data.put("threshold_index", 0.6);
        data.put("triggered", true);
        data.put("timestamp", LocalDateTime.now().toString());
        data.put("message", "High traffic congestion reducing delivery efficiency.");
        return ResponseEntity.ok(data);
    }

    @GetMapping("/zone-alert")
    public ResponseEntity<?> getZoneAlert(@RequestParam(defaultValue = "Koramangala") String zone) {
        Map<String, Object> data = new HashMap<>();
        data.put("type", "zone_alert");
        data.put("zone", zone);
        data.put("risk_score", 85);
        data.put("severity", "high");
        data.put("active_triggers", List.of("heavy_rain", "high_traffic"));
        data.put("timestamp", LocalDateTime.now().toString());
        data.put("message", "Zone risk is HIGH. Multiple disruption triggers active.");
        return ResponseEntity.ok(data);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTriggers(@RequestParam(defaultValue = "Koramangala") String zone) {
        List<Map<String, Object>> triggers = new ArrayList<>();

        Map<String, Object> rain = new HashMap<>();
        rain.put("type", "weather"); rain.put("label", "Rain"); rain.put("value", 88); rain.put("unit", "mm");
        rain.put("severity", "high"); rain.put("triggered", true); rain.put("zone", zone);

        Map<String, Object> aqi = new HashMap<>();
        aqi.put("type", "aqi"); aqi.put("label", "AQI"); aqi.put("value", 185); aqi.put("unit", "AQI");
        aqi.put("severity", "medium"); aqi.put("triggered", true); aqi.put("zone", zone);

        Map<String, Object> traffic = new HashMap<>();
        traffic.put("type", "traffic"); traffic.put("label", "Traffic"); traffic.put("value", 72); traffic.put("unit", "%");
        traffic.put("severity", "medium"); traffic.put("triggered", true); traffic.put("zone", zone);

        Map<String, Object> temp = new HashMap<>();
        temp.put("type", "temperature"); temp.put("label", "Temperature"); temp.put("value", 22); temp.put("unit", "°C");
        temp.put("severity", "low"); temp.put("triggered", false); temp.put("zone", zone);

        triggers.add(rain); triggers.add(aqi); triggers.add(traffic); triggers.add(temp);

        Map<String, Object> resp = new HashMap<>();
        resp.put("zone", zone);
        resp.put("overallRiskScore", 85);
        resp.put("triggers", triggers);
        resp.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveEvent(@RequestBody DisruptionEvent event) {
        event.setEventStart(LocalDateTime.now());
        DisruptionEvent saved = eventRepo.save(event);
        return ResponseEntity.ok(saved);
    }
}
