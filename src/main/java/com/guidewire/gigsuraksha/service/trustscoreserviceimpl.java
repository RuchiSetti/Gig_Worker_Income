package com.guidewire.gigsuraksha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.TrustScore;
import com.guidewire.gigsuraksha.repository.claimrepository;
import com.guidewire.gigsuraksha.repository.trustscorerepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class trustscoreserviceimpl implements trustscoreservice {

    @Autowired
    private trustscorerepository repository;

    @Autowired
    private claimrepository claimRepo;

    @Override
    public void applyDelta(UUID partnerId, String reason, Double amount) {

        Optional<TrustScore> optional = repository.findByPartnerId(partnerId);

        if (optional.isEmpty()) return;

        TrustScore trust = optional.get();

        BigDecimal current = trust.getCurrentScore() == null ? BigDecimal.ONE : trust.getCurrentScore();
        BigDecimal delta = amount == null ? BigDecimal.ZERO : BigDecimal.valueOf(amount);

        trust.setCurrentScore(current.add(delta));
        trust.setLastEvent(reason);
        trust.setUpdatedAt(LocalDateTime.now());

        repository.save(trust);
    }

    @Override
    public BigDecimal getCurrentScore(UUID partnerId) {

        return repository.findByPartnerId(partnerId)
                .map(TrustScore::getCurrentScore)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public void checkSuspension(UUID partnerId) {

        repository.findByPartnerId(partnerId).ifPresent(trust -> {
            if (trust.getCurrentScore() != null &&
                trust.getCurrentScore().compareTo(BigDecimal.valueOf(0.3)) < 0) {
                trust.setIsSuspended(true);
            }
            repository.save(trust);
        });
    }

    @Override
    public String getLabel(UUID partnerId) {

        Optional<TrustScore> optional = repository.findByPartnerId(partnerId);

        if (optional.isPresent()) {
            TrustScore trust = optional.get();
            BigDecimal score = trust.getCurrentScore();

            if (score != null) {
                if (score.compareTo(BigDecimal.valueOf(0.8)) >= 0) return "excellent";
                if (score.compareTo(BigDecimal.valueOf(0.6)) >= 0) return "good";
                if (score.compareTo(BigDecimal.valueOf(0.4)) >= 0) return "average";
                return "risk";
            }
        }

        return "unknown";
    }
}