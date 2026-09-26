package com.tech.ayugram.stripe.stub;

/**
 * Phase 2: Stub for com.stripe.android.TokenCallback
 * Stripe dependency removed in Phase 2
 */
public interface TokenCallback {
    void onSuccess(Token token);
    void onError(Exception error);
}