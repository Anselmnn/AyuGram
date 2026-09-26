package com.tech.ayugram.stripe.stub;

import android.content.Context;

/**
 * Phase 2: Stub for com.stripe.android.net.StripeApiHandler
 * Stripe dependency removed in Phase 2
 */
public class StripeApiHandler {
    public StripeApiHandler(Context context) {}

    public void createToken(Card card, String apiKey, TokenCallback callback) {
        callback.onError(new Exception("Stripe removed in Phase 2"));
    }
}