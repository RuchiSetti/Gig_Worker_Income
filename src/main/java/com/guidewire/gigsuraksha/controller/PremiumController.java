package com.guidewire.gigsuraksha.controller;

import com.guidewire.gigsuraksha.dto.PremiumRequest;
import com.guidewire.gigsuraksha.dto.PremiumResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@RestController
@RequestMapping("/api/premium")
public class PremiumController {

    private static final double K = 0.025;

    // Zone risk multipliers
    private static final Map<String, Double> ZONE_RISK = Map.of(
        "koramangala", 1.3, "whitefield", 1.25, "marathahalli", 1.2,
        "silk board", 1.35, "indiranagar", 1.1, "hsr", 1.05,
        "btm", 1.0, "jp nagar", 0.9, "jayanagar", 0.85, "mg road", 0.95
    );

    @PostMapping("/calculate")
    public ResponseEntity<PremiumResponse> calculate(@RequestBody PremiumRequest req) {
        String tier = req.getCoverageTier() != null ? req.getCoverageTier().toLowerCase() : "pro";
        int coveragePct = switch (tier) { case "basic" -> 30; case "max" -> 80; default -> 60; };
        double coverageMultiplier = coveragePct / 100.0;

        double income = req.getExpectedWeeklyIncome().doubleValue();
        String zone = req.getZone() != null ? req.getZone().toLowerCase() : "koramangala";
        double riskFactor = ZONE_RISK.getOrDefault(zone, 1.0);
        double variability = 0.85; // default income variability
        double crf = 1.1;          // claim risk factor

        double premium = K * income * riskFactor * variability * coverageMultiplier * crf;

        PremiumResponse resp = new PremiumResponse();
        resp.setCoverageTier(tier);
        resp.setCoveragePercent(coveragePct);
        resp.setWeeklyPremium(BigDecimal.valueOf(premium).setScale(2, RoundingMode.HALF_UP));
        resp.setkFactor(BigDecimal.valueOf(K));
        resp.setRiskFactor(BigDecimal.valueOf(riskFactor));
        resp.setVariability(BigDecimal.valueOf(variability));
        resp.setCoverageMultiplier(BigDecimal.valueOf(coverageMultiplier));
        resp.setCrf(BigDecimal.valueOf(crf));
        resp.setExpectedWeeklyIncome(req.getExpectedWeeklyIncome());
        resp.setFormula(String.format("%.3f × %.0f × %.2f × %.2f × %.2f × %.2f = ₹%.2f",
                K, income, riskFactor, variability, coverageMultiplier, crf, premium));
        return ResponseEntity.ok(resp);
    }
}
