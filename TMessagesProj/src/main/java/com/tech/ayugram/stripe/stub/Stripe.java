package com.tech.ayugram.stripe.stub;

import android.content.Context;

/**
 * Phase 2: Stub for com.stripe.android.Stripe
 * Stripe dependency removed in Phase 2
 */
public class Stripe {
    private String apiKey;
    private String publishableKey;

    public Stripe(Context context, String publishableKey) {
        this.publishableKey = publishableKey;
    }

    public void createToken(Card card, TokenCallback callback) {
        // No-op
        callback.onError(new Exception("Stripe removed in Phase 2"));
    }

    public void createToken(Card card, TokenCallback callback, String apiKey) {
        createToken(card, callback);
    }

    public String getPublishableKey() {
        return publishableKey;
    }
}