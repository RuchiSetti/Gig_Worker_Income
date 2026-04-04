package com.guidewire.gigsuraksha.controller;

import com.guidewire.gigsuraksha.entity.InsurancePlan;
import com.guidewire.gigsuraksha.repository.insuranceplanrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/policy")
public class PolicyController {

    @Autowired
    private insuranceplanrepository planRepo;

    private static final List<Map<String, String>> EXCLUSIONS = List.of(
        Map.of("code", "WAR", "label", "War / Armed Conflict",
               "description", "Income loss due to war, civil war, or armed conflict is excluded."),
        Map.of("code", "PANDEMIC", "label", "Pandemic / Epidemic",
               "description", "Government-declared pandemic or epidemic events are excluded."),
        Map.of("code", "TERRORISM", "label", "Terrorism / Civil Unrest",
               "description", "Acts of terrorism or large-scale civil unrest are excluded."),
        Map.of("code", "PLATFORM_OUTAGE", "label", "Platform-Wide Outage",
               "description", "System-wide platform outage affecting all riders (non-local) is excluded."),
        Map.of("code", "SELF_INFLICTED", "label", "Self-Inflicted Inactivity",
               "description", "Voluntary inactivity or self-imposed work stoppage is excluded.")
    );

    private static final Set<String> EXCLUSION_CODES = Set.of(
        "WAR", "PANDEMIC", "TERRORISM", "PLATFORM_OUTAGE", "SELF_INFLICTED"
    );

    @GetMapping("/exclusions")
    public ResponseEntity<?> getExclusions() {
        return ResponseEntity.ok(Map.of("exclusions", EXCLUSIONS, "count", EXCLUSIONS.size()));
    }

    @PostMapping("/check-exclusion")
    public ResponseEntity<?> checkExclusion(@RequestBody Map<String, String> body) {
        String eventCode = body.getOrDefault("eventCode", "").toUpperCase();
        boolean excluded = EXCLUSION_CODES.contains(eventCode);
        Map<String, Object> resp = new HashMap<>();
        resp.put("eventCode", eventCode);
        resp.put("excluded", excluded);
        resp.put("decision", excluded ? "REJECTED" : "ELIGIBLE");
        resp.put("reason", excluded
            ? "This event type is excluded from Zenshield coverage."
            : "Event is eligible for coverage.");
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/plans")
    public ResponseEntity<?> getPlans() {
        List<Map<String, Object>> plans = List.of(
            Map.of("tier", "basic", "label", "Basic", "coveragePercent", 30,
                   "weeklyPremium", 60, "payoutMin", 50, "payoutMax", 150,
                   "icon", "shield",
                   "features", List.of("30% income coverage", "Weather triggers", "Basic fraud protection")),
            Map.of("tier", "pro", "label", "Pro", "coveragePercent", 60,
                   "weeklyPremium", 120, "payoutMin", 120, "payoutMax", 400,
                   "icon", "zap", "recommended", true,
                   "features", List.of("60% income coverage", "All trigger types", "Advanced fraud detection", "Priority payout")),
            Map.of("tier", "max", "label", "Max", "coveragePercent", 80,
                   "weeklyPremium", 200, "payoutMin", 200, "payoutMax", 700,
                   "icon", "crown",
                   "features", List.of("80% income coverage", "All trigger types", "4-layer fraud detection", "Instant payout", "Dedicated support"))
        );
        return ResponseEntity.ok(Map.of("plans", plans));
    }

    @PostMapping("/select")
    public ResponseEntity<?> selectPlan(@RequestBody Map<String, Object> body) {
        String tier = (String) body.get("tier");
        Object partnerIdObj = body.get("partnerId");

        int coveragePct = switch (tier != null ? tier.toLowerCase() : "pro") {
            case "basic" -> 30;
            case "max"   -> 80;
            default      -> 60;
        };
        BigDecimal premium = switch (tier != null ? tier.toLowerCase() : "pro") {
            case "basic" -> BigDecimal.valueOf(60);
            case "max"   -> BigDecimal.valueOf(200);
            default      -> BigDecimal.valueOf(120);
        };

        // Persist to DB if partnerId provided
        if (partnerIdObj != null) {
            try {
                UUID partnerId = UUID.fromString(partnerIdObj.toString());
                // Expire existing active plans for this partner
                planRepo.findByPartnerIdAndStatus(partnerId, "active")
                        .forEach(p -> { p.setStatus("expired"); planRepo.save(p); });

                InsurancePlan plan = new InsurancePlan();
                plan.setPartnerId(partnerId);
                plan.setCoverageTier(tier);
                plan.setCoveragePercentage(coveragePct);
                plan.setWeeklyPremium(premium);
                plan.setWeekStartDate(LocalDate.now());
                plan.setWeekEndDate(LocalDate.now().plusDays(7));
                plan.setStatus("active");
                plan.setCreatedAt(LocalDateTime.now());
                planRepo.save(plan);
            } catch (IllegalArgumentException ignored) {
                // partnerId not a valid UUID (demo mode), skip DB save
            }
        }

        Map<String, Object> resp = new HashMap<>();
        resp.put("partnerId", partnerIdObj);
        resp.put("selectedTier", tier);
        resp.put("coveragePercent", coveragePct);
        resp.put("weeklyPremium", premium);
        resp.put("status", "active");
        resp.put("message", "Plan activated successfully");
        resp.put("weekStartDate", LocalDate.now().toString());
        resp.put("weekEndDate", LocalDate.now().plusDays(7).toString());
        return ResponseEntity.ok(resp);
    }
}
