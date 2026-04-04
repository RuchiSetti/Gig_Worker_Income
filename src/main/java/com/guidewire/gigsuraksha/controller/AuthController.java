package com.guidewire.gigsuraksha.controller;

import com.guidewire.gigsuraksha.entity.DeliveryPartner;
import com.guidewire.gigsuraksha.entity.TrustScore;
import com.guidewire.gigsuraksha.repository.deliverypartnerrepository;
import com.guidewire.gigsuraksha.repository.trustscorerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private deliverypartnerrepository partnerRepo;

    @Autowired
    private trustscorerepository trustRepo;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody DeliveryPartner partner) {
        if (partnerRepo.findByMobileNumber(partner.getMobileNumber()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Mobile already registered"));
        }
        partner.setOnboardedAt(LocalDateTime.now());
        partner.setIsActive(true);
        DeliveryPartner saved = partnerRepo.save(partner);

        TrustScore ts = new TrustScore();
        ts.setPartnerId(saved.getPartnerId());
        ts.setCurrentScore(BigDecimal.valueOf(1.0));
        ts.setLabel("excellent");
        ts.setCleanClaimsCount(0);
        ts.setFraudFlagsCount(0);
        ts.setIsSuspended(false);
        ts.setLastEvent("registered");
        ts.setUpdatedAt(LocalDateTime.now());
        trustRepo.save(ts);

        Map<String, Object> resp = new HashMap<>();
        resp.put("partnerId", saved.getPartnerId());
        resp.put("name", saved.getFullName());
        resp.put("mobile", saved.getMobileNumber());
        resp.put("message", "Registration successful");
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String mobile = body.get("mobile");
        Optional<DeliveryPartner> opt = partnerRepo.findByMobileNumber(mobile);
        if (opt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Partner not found"));
        }
        DeliveryPartner p = opt.get();
        Map<String, Object> resp = new HashMap<>();
        resp.put("partnerId", p.getPartnerId());
        resp.put("name", p.getFullName());
        resp.put("mobile", p.getMobileNumber());
        resp.put("platform", p.getPlatform());
        resp.put("city", p.getCity());
        resp.put("zoneId", p.getZoneId());
        resp.put("token", "zenshield-token-" + p.getPartnerId());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/profile/{partnerId}")
    public ResponseEntity<?> getProfile(@PathVariable java.util.UUID partnerId) {
        return partnerRepo.findById(partnerId)
                .map(p -> {
                    Map<String, Object> resp = new HashMap<>();
                    resp.put("partnerId", p.getPartnerId());
                    resp.put("name", p.getFullName());
                    resp.put("mobile", p.getMobileNumber());
                    resp.put("platform", p.getPlatform());
                    resp.put("city", p.getCity());
                    resp.put("zoneId", p.getZoneId());
                    resp.put("isActive", p.getIsActive());
                    trustRepo.findByPartnerId(p.getPartnerId()).ifPresent(ts -> {
                        resp.put("trustScore", ts.getCurrentScore());
                        resp.put("trustLabel", ts.getLabel());
                    });
                    return ResponseEntity.ok(resp);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
