package com.guidewire.gigsuraksha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.Claim;
import com.guidewire.gigsuraksha.entity.IncomeProfile;
import com.guidewire.gigsuraksha.entity.IncomeRecord;
import com.guidewire.gigsuraksha.repository.claimrepository;
import com.guidewire.gigsuraksha.repository.incomeprofilerepository;
import com.guidewire.gigsuraksha.repository.incomerecordrepository;
import com.guidewire.gigsuraksha.repository.trustscorerepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class claimserviceimpl implements claimservice {

    @Autowired
    private claimrepository repository;
    @Autowired
    private incomerecordrepository incomeRecordRepo;

    @Autowired
    private incomeprofilerepository incomeProfileRepo;

    @Autowired
    private trustscorerepository trustRepo;

    @Override
    public void initiateClaim(UUID triggerId, UUID partnerId, UUID planId) {

        Claim claim = new Claim();

        claim.setPartnerId(partnerId);
        claim.setPlanId(planId);
        claim.setTriggerEventId(triggerId);
        claim.setClaimDate(LocalDate.now());
        claim.setIncomeLoss(200.0);
        claim.setCoveragePercentage(60);
        claim.setTrustScoreAtClaim(0.8);
        claim.setFinalPayout(0.0);
        claim.setStatus("pending");
        claim.setFraudProbability(0.1);
        claim.setInitiatedAt(LocalDateTime.now());

        repository.save(claim);
    }

    @Override
    public void evaluateLoss(UUID claimId) {

        Optional<Claim> optional = repository.findById(claimId);

        if (optional.isPresent()) {

            Claim claim = optional.get();

            UUID partnerId = claim.getPartnerId();

            IncomeRecord record = incomeRecordRepo
                .findByPartnerIdAndRecordDate(partnerId, claim.getClaimDate())
                .orElse(null);

            IncomeProfile profile = incomeProfileRepo
                .findByPartnerId(partnerId)
                .orElse(null);

            if (record != null && profile != null) {

                Double loss = profile.getPredictedDailyIncome() - record.getActualIncome();

                claim.setIncomeLoss(loss);

                Double payout = loss * claim.getCoveragePercentage() / 100;

                claim.setFinalPayout(payout);

                repository.save(claim);
            }
        }
    }

    @Override
    public void runFraudCheck(UUID claimId) {

        Optional<Claim> optional = repository.findById(claimId);

        if (optional.isPresent()) {
            Claim claim = optional.get();

            claim.setFraudProbability(0.2);

            if (claim.getFraudProbability() > 0.7) {
                claim.setStatus("flagged");
            }

            repository.save(claim);
        }
    }

    @Override
    public void approveClaim(UUID claimId) {

        Optional<Claim> optional = repository.findById(claimId);

        if (optional.isPresent()) {
            Claim claim = optional.get();

            claim.setStatus("approved");
            claim.setResolvedAt(LocalDateTime.now());

            repository.save(claim);
        }
    }

    @Override
    public void rejectClaim(UUID claimId, String reason) {

        Optional<Claim> optional = repository.findById(claimId);

        if (optional.isPresent()) {
            Claim claim = optional.get();

            claim.setStatus("rejected");
            claim.setResolvedAt(LocalDateTime.now());

            repository.save(claim);
        }
    }
}