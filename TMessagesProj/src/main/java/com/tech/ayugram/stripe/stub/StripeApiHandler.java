package com.tech.ayugram.stripe.stub;

import android.content.Context;

public class StripeApiHandler {
    public StripeApiHandler(Context context) {}

    public void createToken(Card card, String apiKey, TokenCallback callback) {
        callback.onError(new Exception("Stripe removed in Phase 2"));
    }
}