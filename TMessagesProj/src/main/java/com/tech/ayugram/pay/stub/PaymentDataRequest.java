package com.tech.ayugram.pay.stub;

import org.json.JSONObject;

public class PaymentDataRequest {
    private JSONObject json;

    public static PaymentDataRequest fromJson(JSONObject json) { return new PaymentDataRequest(); }
    public JSONObject toJson() { return new JSONObject(); }
}
