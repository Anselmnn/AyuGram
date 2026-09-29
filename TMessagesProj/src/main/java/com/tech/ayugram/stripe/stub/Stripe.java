package com.tech.ayugram.stripe.stub;

import android.content.Context;
import com.tech.ayugram.stripe.stub.model.Card;
import com.tech.ayugram.stripe.stub.model.Token;

public class Stripe {
    public static final String VERSION = "2026.09.29.4";

    private String apiKey;
    private String publishableKey;

    public Stripe(Context context, String publishableKey) {
        this.publishableKey = publishableKey;
    }

    public Stripe(String publishableKey) {
        this.publishableKey = publishableKey;
    }

    public void createToken(Card card, TokenCallback callback) {
        callback.onError(new Exception("Stripe removed in Phase 2"));
    }

    public void createToken(Card card, TokenCallback callback, String apiKey) {
        createToken(card, callback);
    }

    public String getPublishableKey() {
        return publishableKey;
    }

    public interface TokenCallback {
        void onSuccess(Token token);
        void onError(Exception error);
    }
}