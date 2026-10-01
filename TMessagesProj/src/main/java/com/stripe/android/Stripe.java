package com.stripe.android;

import android.content.Context;

public class Stripe {
    private Context context;
    private String publishableKey;

    public Stripe(Context context, String publishableKey) {
        this.context = context;
        this.publishableKey = publishableKey;
    }

    public com.google.android.gms.tasks.Task<Token> createToken(Card card, String type) {
        return null;
    }

    public com.google.android.gms.tasks.Task<Token> createToken(Card card) {
        return createToken(card, "card");
    }
}