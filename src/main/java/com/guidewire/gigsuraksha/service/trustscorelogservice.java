package com.guidewire.gigsuraksha.service;
import java.util.List;
import java.util.UUID;

import com.guidewire.gigsuraksha.entity.TrustScoreLog;
public interface trustscorelogservice {
	void appendLog(UUID partnerId, String event, Double delta,
            Double scoreBefore, Double scoreAfter, UUID relatedClaimId);

List<TrustScoreLog> getHistory(UUID partnerId);

List<TrustScoreLog> getRolling30Days(UUID partnerId);
	}

