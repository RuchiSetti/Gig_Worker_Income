package com.guidewire.gigsuraksha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidewire.gigsuraksha.entity.TrustScoreLog;
import com.guidewire.gigsuraksha.repository.trustscorelogrepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class trustscorelogserviceimpl implements trustscorelogservice {

    @Autowired
    private trustscorelogrepository repository;

    @Override
    public void appendLog(UUID partnerId, String event, Double delta,
                          Double scoreBefore, Double scoreAfter, UUID relatedClaimId) {

        TrustScoreLog log = new TrustScoreLog();

        log.setLogId(UUID.randomUUID());
        log.setPartnerId(partnerId);
        log.setEventType(event);

        // convert Double to BigDecimal safely
        log.setDelta(delta == null ? BigDecimal.ZERO : BigDecimal.valueOf(delta));
        log.setScoreBefore(scoreBefore == null ? BigDecimal.ZERO : BigDecimal.valueOf(scoreBefore));
        log.setScoreAfter(scoreAfter == null ? BigDecimal.ZERO : BigDecimal.valueOf(scoreAfter));

        log.setRelatedClaimId(relatedClaimId);
        log.setLoggedAt(LocalDateTime.now());

        repository.save(log);
    }

    @Override
    public List<TrustScoreLog> getHistory(UUID partnerId) {
        return repository.findAll().stream()
                .filter(log -> log.getPartnerId().equals(partnerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrustScoreLog> getRolling30Days(UUID partnerId) {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(30);

        return repository.findAll().stream()
                .filter(log -> log.getPartnerId().equals(partnerId)
                        && log.getLoggedAt().isAfter(cutoff))
                .collect(Collectors.toList());
    }
}