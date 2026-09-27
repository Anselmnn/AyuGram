package com.tech.ayugram.pay.stub;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDataRequest {
    public static PaymentDataRequest fromJson(String json) throws org.json.JSONException {
        return new PaymentDataRequest();
    }

    public String toJson() {
        return "{}";
    }
}