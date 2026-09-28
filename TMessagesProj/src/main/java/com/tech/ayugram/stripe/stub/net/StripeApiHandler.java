package com.tech.ayugram.stripe.stub.net;

import android.content.Context;
import com.tech.ayugram.stripe.stub.model.Card;
import com.tech.ayugram.stripe.stub.TokenCallback;

public class StripeApiHandler {
    public static final String VERSION = "2023-08-16";

    public StripeApiHandler(Context context) {}

    public void createToken(Card card, String apiKey, com.tech.ayugram.stripe.stub.TokenCallback callback) {
        callback.onError(new Exception("Stripe removed in Phase 2"));
    }
}