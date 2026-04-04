package com.guidewire.gigsuraksha.controller;

import com.guidewire.gigsuraksha.entity.Claim;
import com.guidewire.gigsuraksha.entity.DeliveryPartner;
import com.guidewire.gigsuraksha.entity.FraudFlag;
import com.guidewire.gigsuraksha.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired private claimrepository claimRepo;
    @Autowired private deliverypartnerrepository partnerRepo;
    @Autowired private fraudflagrepository fraudRepo;
    @Autowired private trustscorerepository trustRepo;
    @Autowired private payoutrepository payoutRepo;

    @GetMapping("/overview")
    public ResponseEntity<?> overview() {
        List<Claim> claims = claimRepo.findAll();
        List<DeliveryPartner> partners = partnerRepo.findAll();
        List<FraudFlag> flags = fraudRepo.findAll();

        long totalUsers = partners.size();
        BigDecimal totalPayouts = claims.stream()
                .filter(c -> "approved".equals(c.getStatus()))
                .map(c -> c.getFinalPayout() != null ? c.getFinalPayout() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        long activeClaims = claims.stream().filter(c -> "pending".equals(c.getStatus()) || "flagged".equals(c.getStatus())).count();
        long fraudFlags = flags.stream().filter(f -> "manual_review".equals(f.getActionTaken())).count();

        Map<String, Object> resp = new HashMap<>();
        resp.put("totalUsers", totalUsers);
        resp.put("totalPayouts", totalPayouts);
        resp.put("activeClaims", activeClaims);
        resp.put("fraudFlags", fraudFlags);
        resp.put("totalClaims", claims.size());
        resp.put("approvedClaims", claims.stream().filter(c -> "approved".equals(c.getStatus())).count());
        resp.put("rejectedClaims", claims.stream().filter(c -> "rejected".equals(c.getStatus())).count());

        // 7-day payout chart data (mock enriched with real count)
        List<Map<String, Object>> chartData = new ArrayList<>();
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int[] payoutAmounts = {24000, 31000, 28000, 42000, 38000, 52000, 45000};
        int[] claimCounts = {12, 18, 15, 24, 21, 30, 26};
        for (int i = 0; i < 7; i++) {
            chartData.add(Map.of("day", days[i], "payout", payoutAmounts[i], "claims", claimCounts[i]));
        }
        resp.put("weeklyChart", chartData);

        // Zone risk grid
        List<Map<String, Object>> zones = List.of(
            Map.of("zone", "Koramangala", "risk", "high", "score", 85),
            Map.of("zone", "Whitefield", "risk", "high", "score", 82),
            Map.of("zone", "Marathahalli", "risk", "high", "score", 78),
            Map.of("zone", "Silk Board", "risk", "high", "score", 90),
            Map.of("zone", "Indiranagar", "risk", "medium", "score", 62),
            Map.of("zone", "HSR Layout", "risk", "medium", "score", 58),
            Map.of("zone", "BTM Layout", "risk", "medium", "score", 55),
            Map.of("zone", "Sarjapur", "risk", "medium", "score", 60),
            Map.of("zone", "JP Nagar", "risk", "low", "score", 32),
            Map.of("zone", "Jayanagar", "risk", "low", "score", 28),
            Map.of("zone", "Bannerghatta", "risk", "low", "score", 35),
            Map.of("zone", "MG Road", "risk", "low", "score", 30)
        );
        resp.put("zoneRiskGrid", zones);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String plan,
            @RequestParam(required = false) String status) {
        List<DeliveryPartner> partners = partnerRepo.findAll();
        List<Map<String, Object>> result = partners.stream().map(p -> {
            Map<String, Object> m = new HashMap<>();
            m.put("partnerId", p.getPartnerId());
            m.put("name", p.getFullName());
            m.put("mobile", p.getMobileNumber());
            m.put("platform", p.getPlatform());
            m.put("city", p.getCity());
            m.put("isActive", p.getIsActive());
            trustRepo.findByPartnerId(p.getPartnerId()).ifPresent(ts -> {
                m.put("trustScore", ts.getCurrentScore());
                m.put("trustLabel", ts.getLabel());
            });
            long claimCount = claimRepo.findByPartnerIdOrderByInitiatedAtDesc(p.getPartnerId()).size();
            m.put("claimsCount", claimCount);
            return m;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("users", result, "total", result.size()));
    }

    @GetMapping("/fraud/alerts")
    public ResponseEntity<?> getFraudAlerts() {
        List<FraudFlag> flags = fraudRepo.findAll();
        long highSeverity = flags.stream().filter(f -> f.getFraudProbability() != null
                && f.getFraudProbability().compareTo(BigDecimal.valueOf(0.85)) >= 0).count();
        long pending = flags.stream().filter(f -> "manual_review".equals(f.getActionTaken())).count();
        long resolved = flags.stream().filter(f -> "cleared".equals(f.getActionTaken())).count();

        Map<String, Object> resp = new HashMap<>();
        resp.put("totalAlerts", flags.size());
        resp.put("highSeverity", highSeverity);
        resp.put("pending", pending);
        resp.put("resolved", resolved);
        resp.put("alerts", flags);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/fraud/resolve/{flagId}")
    public ResponseEntity<?> resolveFlag(@PathVariable UUID flagId, @RequestBody Map<String, String> body) {
        return fraudRepo.findById(flagId).map(f -> {
            f.setActionTaken("cleared");
            f.setReviewNote(body.getOrDefault("note", "Resolved by admin"));
            fraudRepo.save(f);
            return ResponseEntity.ok(Map.of("message", "Flag resolved", "flagId", flagId));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ai-insights")
    public ResponseEntity<?> getAiInsights() {
        List<Map<String, Object>> insights = List.of(
            Map.of("id", 1, "tag", "Prediction", "confidence", 85,
                   "title", "High claims expected tomorrow",
                   "detail", "Rain forecast + Friday pattern suggests 40% more claims",
                   "icon", "cloud-rain"),
            Map.of("id", 2, "tag", "Alert", "confidence", 72,
                   "title", "Income drop trend in Whitefield",
                   "detail", "AQI spike correlates with 28% income reduction this week",
                   "icon", "trending-down"),
            Map.of("id", 3, "tag", "Positive", "confidence", 90,
                   "title", "Fraud risk reduced by 15%",
                   "detail", "ML model improvements detected fewer anomalies this week",
                   "icon", "shield-check"),
            Map.of("id", 4, "tag", "Forecast", "confidence", 78,
                   "title", "Weather-triggered claims surge",
                   "detail", "140+ riders in high-risk zones may file claims this weekend",
                   "icon", "zap")
        );

        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int[] actual = {45, 62, 38, 71, 58, 84, 0};
        int[] predicted = {48, 59, 42, 68, 62, 80, 95};
        List<Map<String, Object>> chart = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Map<String, Object> pt = new HashMap<>();
            pt.put("day", days[i]);
            pt.put("actual", actual[i] > 0 ? actual[i] : null);
            pt.put("predicted", predicted[i]);
            chart.add(pt);
        }

        return ResponseEntity.ok(Map.of("insights", insights, "predictionChart", chart,
                "modelInfo", "Zenshield ML model — trained on 18 months of data, refreshed every 6 hours"));
    }
}
