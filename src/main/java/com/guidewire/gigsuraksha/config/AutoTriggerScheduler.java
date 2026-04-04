package com.guidewire.gigsuraksha.config;

import com.guidewire.gigsuraksha.entity.DeliveryPartner;
import com.guidewire.gigsuraksha.entity.InsurancePlan;
import com.guidewire.gigsuraksha.entity.TrustScore;
import com.guidewire.gigsuraksha.repository.*;
import com.guidewire.gigsuraksha.entity.Claim;
import com.guidewire.gigsuraksha.entity.FraudFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
@EnableScheduling
public class AutoTriggerScheduler {

    @Autowired private deliverypartnerrepository partnerRepo;
    @Autowired private claimrepository claimRepo;
    @Autowired private trustscorerepository trustRepo;
    @Autowired private insuranceplanrepository planRepo;
    @Autowired private fraudflagrepository fraudRepo;

    private final Random rng = new Random();

    // Runs every 60 seconds — simulates zero-touch income monitoring
    @Scheduled(fixedDelay = 60000)
    public void autoMonitorIncome() {
        List<DeliveryPartner> activePartners = partnerRepo.findAll().stream()
                .filter(p -> Boolean.TRUE.equals(p.getIsActive()))
                .toList();

        for (DeliveryPartner partner : activePartners) {
            // Simulate today's income (60-100% of expected ₹1000)
            double expectedIncome = 1000.0;
            double actualIncome = expectedIncome * (0.4 + rng.nextDouble() * 0.6);
            double loss = expectedIncome - actualIncome;

            // Z-score check: only trigger if significant drop (Z ≤ -1.5)
            double stdDev = 150.0;
            double zScore = (actualIncome - expectedIncome) / stdDev;
            if (zScore > -1.5) continue; // No significant drop, skip

            // Check no duplicate claim today
            LocalDate today = LocalDate.now();
            boolean alreadyClaimed = claimRepo.findByPartnerIdOrderByInitiatedAtDesc(partner.getPartnerId())
                    .stream().anyMatch(c -> today.equals(c.getClaimDate()));
            if (alreadyClaimed) continue;

            // Get active plan
            InsurancePlan plan = planRepo.findByPartnerIdAndStatus(partner.getPartnerId(), "active")
                    .stream().findFirst().orElse(null);
            int coveragePct = plan != null ? plan.getCoveragePercentage() : 60;

            // Get trust score
            TrustScore ts = trustRepo.findByPartnerId(partner.getPartnerId()).orElse(null);
            double trustScore = ts != null ? ts.getCurrentScore().doubleValue() : 0.9;

            // Fraud check: simple ML simulation
            double fraudProb = loss / expectedIncome > 0.9 ? 0.75 : 0.2;
            String status = fraudProb > 0.85 ? "rejected" : fraudProb > 0.65 ? "flagged" : "approved";

            // Calculate payout
            double payout = loss * (coveragePct / 100.0) * trustScore;

            // Save auto-generated claim
            Claim claim = new Claim();
            claim.setPartnerId(partner.getPartnerId());
            claim.setClaimDate(today);
            claim.setIncomeLoss(BigDecimal.valueOf(loss).setScale(2, RoundingMode.HALF_UP));
            claim.setCoveragePercentage(coveragePct);
            claim.setTrustScoreAtClaim(BigDecimal.valueOf(trustScore));
            claim.setFinalPayout(BigDecimal.valueOf(payout).setScale(2, RoundingMode.HALF_UP));
            claim.setFraudProbability(BigDecimal.valueOf(fraudProb));
            claim.setStatus(status);
            claim.setInitiatedAt(LocalDateTime.now());
            claim.setResolvedAt("approved".equals(status) ? LocalDateTime.now() : null);
            if (plan != null) claim.setPlanId(plan.getPlanId());
            claimRepo.save(claim);

            // Update trust score
            if ("approved".equals(status) && ts != null) {
                double newScore = Math.min(1.0, ts.getCurrentScore().doubleValue() + 0.01);
                ts.setCurrentScore(BigDecimal.valueOf(newScore).setScale(3, RoundingMode.HALF_UP));
                ts.setCleanClaimsCount(ts.getCleanClaimsCount() + 1);
                ts.setUpdatedAt(LocalDateTime.now());
                trustRepo.save(ts);
            }
        }
    }
}
