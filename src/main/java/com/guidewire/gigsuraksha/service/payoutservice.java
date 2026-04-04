package com.guidewire.gigsuraksha.service;

import java.util.UUID;

import com.guidewire.gigsuraksha.entity.PayOut;

public interface payoutservice {

    void initiateUPIPayout(UUID claimId);

    void verifyPaymentStatus(UUID payoutId);

    void retryFailedPayout(UUID payoutId);
}
