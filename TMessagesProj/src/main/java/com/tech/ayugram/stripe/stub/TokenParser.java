package com.tech.ayugram.stripe.stub;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Phase 2: Stub for com.stripe.android.net.TokenParser
 * Stripe dependency removed in Phase 2
 */
public class TokenParser {
    public static Token parse(String json) throws JSONException {
        return new Token();
    }

    public static Token parse(JSONObject jsonObject) {
        return new Token();
    }
}