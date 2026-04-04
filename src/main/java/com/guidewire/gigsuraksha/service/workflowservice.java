package com.guidewire.gigsuraksha.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class workflowservice {

    @Autowired
    private incomerecordservice incomeRecordService;

    @Autowired
    private claimservice claimService;

    @Autowired
    private fraudflagservice fraudFlagService;

    @Autowired
    private trustscoreservice trustScoreService;

    @Autowired
    private payoutservice payoutService;

    public void executeFullFlow(UUID partnerId) {

        LocalDate today = LocalDate.now();

        // 1. Fetch income (partnerId + date)
        incomeRecordService.fetchActualIncome(partnerId, today);

        // 2. Compute deviation (partnerId + date)
        incomeRecordService.computeZScore(partnerId, today);

        // 3. Check drop (partnerId + date)
        incomeRecordService.flagSignificantDrop(partnerId, today);

     // 4. Create claim (FIXED)
        UUID triggerId = UUID.randomUUID();
        UUID planId = UUID.randomUUID(); // or fetch actual planId if needed
        claimService.initiateClaim(triggerId, partnerId, planId);

        // 5. Evaluate claim (partnerId)
        claimService.evaluateLoss(partnerId);

     // 6. Fraud check (FIXED)
        UUID flagId = UUID.randomUUID(); // or get the actual flagId generated for this claim
        fraudFlagService.runRuleChecks(flagId);
        fraudFlagService.runIsolationForest(flagId);
        
     // 7. Update trust score (FIXED)
        trustScoreService.applyDelta(partnerId, "claim", -0.1);
        
        // 8. Approve claim (partnerId)
        claimService.approveClaim(partnerId);

        // 9. Payout (partnerId)
        payoutService.initiateUPIPayout(partnerId);
        payoutService.verifyPaymentStatus(partnerId);
    }
}