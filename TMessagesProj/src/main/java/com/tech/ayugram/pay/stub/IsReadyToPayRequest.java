package com.tech.ayugram.pay.stub;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Phase 2: Stub for com.google.android.gms.wallet.IsReadyToPayRequest
 * Google Pay dependency removed in Phase 2
 */
public class IsReadyToPayRequest {
    public static IsReadyToPayRequest fromJson(String json) throws JSONException {
        return new IsReadyToPayRequest();
    }

    public static IsReadyToPayRequest fromJson(JSONObject json) {
        return new IsReadyToPayRequest();
    }
}