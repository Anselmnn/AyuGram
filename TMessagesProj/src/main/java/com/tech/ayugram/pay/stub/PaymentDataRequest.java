package com.tech.ayugram.pay.stub;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Phase 2: Stub for com.google.android.gms.wallet.PaymentDataRequest
 * Google Pay dependency removed in Phase 2
 */
public class PaymentDataRequest {
    public static PaymentDataRequest fromJson(String json) throws JSONException {
        return new PaymentDataRequest();
    }

    public String toJson() {
        return "{}";
    }
}