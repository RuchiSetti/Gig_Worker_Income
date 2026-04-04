package com.guidewire.gigsuraksha.controller;

import com.guidewire.gigsuraksha.dto.ClaimEvaluateRequest;
import com.guidewire.gigsuraksha.dto.ClaimEvaluateResponse;
import com.guidewire.gigsuraksha.entity.Claim;
import com.guidewire.gigsuraksha.entity.FraudFlag;
import com.guidewire.gigsuraksha.entity.InsurancePlan;
import com.guidewire.gigsuraksha.entity.TrustScore;
import com.guidewire.gigsuraksha.repository.claimrepository;
import com.guidewire.gigsuraksha.repository.fraudflagrepository;
import com.guidewire.gigsuraksha.repository.insuranceplanrepository;
import com.guidewire.gigsuraksha.repository.trustscorerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/claim")
public class ClaimEvaluationController {

    @Autowired private claimrepository claimRepo;
    @Autowired private fraudflagrepository fraudRepo;
    @Autowired private trustscorerepository trustRepo;
    @Autowired private insuranceplanrepository planRepo;

    private static final BigDecimal STD_DEV = BigDecimal.valueOf(150.0);
    private static final BigDecimal Z_THRESHOLD = BigDecimal.valueOf(-1.5);

    @PostMapping("/evaluate")
    public ResponseEntity<?> evaluate(@RequestBody ClaimEvaluateRequest req) {
        ClaimEvaluateResponse resp = new ClaimEvaluateResponse();

        BigDecimal loss = req.getExpectedIncome().subtract(req.getActualIncome());
        resp.setExpectedIncome(req.getExpectedIncome());
        resp.setActualIncome(req.getActualIncome());
        resp.setLoss(loss);

        // Z-Score validation
        BigDecimal zScore = req.getActualIncome().subtract(req.getExpectedIncome())
                .divide(STD_DEV, 4, RoundingMode.HALF_UP);
        resp.setzScore(zScore);

        if (zScore.compareTo(Z_THRESHOLD) > 0) {
            resp.setStatus("rejected");
            resp.setRejectionReason("Z-score " + zScore + " does not meet threshold of -1.5. Loss not significant.");
            resp.setFinalPayout(BigDecimal.ZERO);
            return ResponseEntity.ok(resp);
        }

        // Fetch active plan
        InsurancePlan activePlan = planRepo.findByPartnerIdAndStatus(req.getPartnerId(), "active")
                .stream().findFirst().orElse(null);
        int coveragePct = activePlan != null ? activePlan.getCoveragePercentage() : 60;
        resp.setCoveragePercent(BigDecimal.valueOf(coveragePct));

        // Fetch trust score
        TrustScore ts = trustRepo.findByPartnerId(req.getPartnerId()).orElse(null);
        BigDecimal trustScore = ts != null ? ts.getCurrentScore() : BigDecimal.valueOf(0.9);
        resp.setTrustScore(trustScore);

        // 4-Layer Fraud Detection
        FraudResult fraud = runFraudDetection(req, loss);
        resp.setFraudDecision(fraud.decision);

        if ("REJECTED".equals(fraud.decision)) {
            resp.setStatus("rejected");
            resp.setRejectionReason("Fraud detected: " + fraud.reason);
            resp.setFinalPayout(BigDecimal.ZERO);
            saveFraudFlag(req, fraud, null);
            updateTrustScore(ts, req.getPartnerId(), -0.15);
            return ResponseEntity.ok(resp);
        }

        // Payout = loss × (coverage/100) × trustScore
        BigDecimal payout = loss
                .multiply(BigDecimal.valueOf(coveragePct).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP))
                .multiply(trustScore)
                .setScale(2, RoundingMode.HALF_UP);

        resp.setFinalPayout(payout);
        resp.setFormula("₹" + loss + " × " + (coveragePct / 100.0) + " × " + trustScore + " = ₹" + payout);

        // Save claim
        Claim claim = new Claim();
        claim.setPartnerId(req.getPartnerId());
        claim.setClaimDate(LocalDate.now());
        claim.setIncomeLoss(loss);
        claim.setCoveragePercentage(coveragePct);
        claim.setTrustScoreAtClaim(trustScore);
        claim.setFinalPayout(payout);
        claim.setFraudProbability(BigDecimal.valueOf(fraud.probability));
        claim.setStatus("FLAGGED".equals(fraud.decision) ? "flagged" : "approved");
        claim.setInitiatedAt(LocalDateTime.now());
        claim.setResolvedAt(LocalDateTime.now());
        if (activePlan != null) claim.setPlanId(activePlan.getPlanId());
        Claim saved = claimRepo.save(claim);

        resp.setClaimId(saved.getClaimId());
        resp.setStatus(saved.getStatus());

        if ("FLAGGED".equals(fraud.decision)) {
            saveFraudFlag(req, fraud, saved.getClaimId());
        } else {
            updateTrustScore(ts, req.getPartnerId(), 0.01);
        }

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/history/{partnerId}")
    public ResponseEntity<?> getHistory(@PathVariable UUID partnerId) {
        List<Claim> claims = claimRepo.findByPartnerIdOrderByInitiatedAtDesc(partnerId);
        return ResponseEntity.ok(Map.of("claims", claims, "total", claims.size()));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllClaims() {
        List<Claim> claims = claimRepo.findAll();
        return ResponseEntity.ok(Map.of("claims", claims, "total", claims.size()));
    }

    @PostMapping("/approve/{claimId}")
    public ResponseEntity<?> approve(@PathVariable UUID claimId) {
        return claimRepo.findById(claimId).map(c -> {
            c.setStatus("approved");
            c.setResolvedAt(LocalDateTime.now());
            claimRepo.save(c);
            return ResponseEntity.ok(Map.of("message", "Claim approved", "claimId", claimId));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/reject/{claimId}")
    public ResponseEntity<?> reject(@PathVariable UUID claimId, @RequestBody Map<String, String> body) {
        return claimRepo.findById(claimId).map(c -> {
            c.setStatus("rejected");
            c.setResolvedAt(LocalDateTime.now());
            claimRepo.save(c);
            return ResponseEntity.ok(Map.of("message", "Claim rejected", "reason", body.getOrDefault("reason", "")));
        }).orElse(ResponseEntity.notFound().build());
    }

    // ── 4-Layer Fraud Detection ──────────────────────────────────────────────

    private FraudResult runFraudDetection(ClaimEvaluateRequest req, BigDecimal loss) {
        // Layer A: Rule-based
        if (isGpsMismatch(req)) return new FraudResult("REJECTED", "GPS location mismatch with declared zone", 0.92);
        if (isDuplicateClaim(req.getPartnerId())) return new FraudResult("REJECTED", "Duplicate claim within 24 hours", 0.95);
        if (isSuddenInactivity(req)) return new FraudResult("FLAGGED", "Sudden inactivity pattern detected", 0.70);

        // Layer B: ML simulation (fraud probability based on loss magnitude)
        double mlProb = simulateMLScore(req, loss);
        if (mlProb > 0.85) return new FraudResult("REJECTED", "ML model high fraud probability: " + mlProb, mlProb);
        if (mlProb > 0.65) return new FraudResult("FLAGGED", "ML model flagged for review: " + mlProb, mlProb);

        // Layer C: Anomaly detection
        if (isAbnormalClaimFrequency(req.getPartnerId())) return new FraudResult("FLAGGED", "Abnormal claim frequency", 0.68);

        // Layer D: Trust score gate
        TrustScore ts = trustRepo.findByPartnerId(req.getPartnerId()).orElse(null);
        if (ts != null && ts.getCurrentScore().compareTo(BigDecimal.valueOf(0.3)) < 0) {
            return new FraudResult("REJECTED", "Trust score too low: " + ts.getCurrentScore(), 0.88);
        }

        return new FraudResult("CLEAN", "All fraud checks passed", mlProb);
    }

    private boolean isGpsMismatch(ClaimEvaluateRequest req) {
        // Mock: if GPS coords are null or wildly off from zone, flag
        if (req.getGpsLat() == null || req.getGpsLon() == null) return false;
        // Bengaluru bounds: lat 12.8-13.1, lon 77.4-77.8
        boolean latOk = req.getGpsLat().compareTo(BigDecimal.valueOf(12.7)) > 0
                && req.getGpsLat().compareTo(BigDecimal.valueOf(13.2)) < 0;
        boolean lonOk = req.getGpsLon().compareTo(BigDecimal.valueOf(77.3)) > 0
                && req.getGpsLon().compareTo(BigDecimal.valueOf(77.9)) < 0;
        return !latOk || !lonOk;
    }

    private boolean isDuplicateClaim(UUID partnerId) {
        LocalDate today = LocalDate.now();
        long count = claimRepo.findByPartnerIdOrderByInitiatedAtDesc(partnerId).stream()
                .filter(c -> c.getClaimDate() != null && c.getClaimDate().equals(today))
                .count();
        return count >= 2;
    }

    private boolean isSuddenInactivity(ClaimEvaluateRequest req) {
        // Mock: if actual income is exactly 0 with no trigger context, suspicious
        return req.getActualIncome().compareTo(BigDecimal.ZERO) == 0
                && (req.getZone() == null || req.getZone().isBlank());
    }

    private double simulateMLScore(ClaimEvaluateRequest req, BigDecimal loss) {
        // Simulate XGBoost: higher loss relative to expected = lower fraud probability
        if (req.getExpectedIncome().compareTo(BigDecimal.ZERO) == 0) return 0.9;
        double lossRatio = loss.doubleValue() / req.getExpectedIncome().doubleValue();
        // Very high loss ratio (>90%) is suspicious
        if (lossRatio > 0.9) return 0.75;
        if (lossRatio > 0.7) return 0.45;
        return 0.2;
    }

    private boolean isAbnormalClaimFrequency(UUID partnerId) {
        LocalDate weekAgo = LocalDate.now().minusDays(7);
        long count = claimRepo.findByPartnerIdOrderByInitiatedAtDesc(partnerId).stream()
                .filter(c -> c.getClaimDate() != null && c.getClaimDate().isAfter(weekAgo))
                .count();
        return count >= 5;
    }

    private void saveFraudFlag(ClaimEvaluateRequest req, FraudResult fraud, UUID claimId) {
        FraudFlag flag = new FraudFlag();
        flag.setClaimId(claimId);
        flag.setPartnerId(req.getPartnerId());
        flag.setDetectionLayer("multi_layer");
        flag.setFraudProbability(BigDecimal.valueOf(fraud.probability).setScale(3, RoundingMode.HALF_UP));
        flag.setRuleTriggered(fraud.reason);
        flag.setGpsConsistencyScore(req.getGpsLat() != null ? BigDecimal.valueOf(0.5) : BigDecimal.valueOf(1.0));
        flag.setActionTaken("REJECTED".equals(fraud.decision) ? "auto_rejected" : "manual_review");
        flag.setFlaggedAt(LocalDateTime.now());
        fraudRepo.save(flag);
    }

    private void updateTrustScore(TrustScore ts, UUID partnerId, double delta) {
        if (ts == null) return;
        double newScore = Math.max(0.0, Math.min(1.0, ts.getCurrentScore().doubleValue() + delta));
        ts.setCurrentScore(BigDecimal.valueOf(newScore).setScale(3, RoundingMode.HALF_UP));
        ts.setLabel(newScore >= 0.8 ? "excellent" : newScore >= 0.6 ? "good" : newScore >= 0.4 ? "average" : "risk");
        ts.setUpdatedAt(LocalDateTime.now());
        if (delta > 0) ts.setCleanClaimsCount(ts.getCleanClaimsCount() + 1);
        else ts.setFraudFlagsCount(ts.getFraudFlagsCount() + 1);
        trustRepo.save(ts);
    }

    static class FraudResult {
        String decision; String reason; double probability;
        FraudResult(String d, String r, double p) { decision = d; reason = r; probability = p; }
    }
}
