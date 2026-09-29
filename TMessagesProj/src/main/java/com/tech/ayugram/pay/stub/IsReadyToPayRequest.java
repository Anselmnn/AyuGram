package com.tech.ayugram.pay.stub;

import org.json.JSONException;
import org.json.JSONObject;

public class IsReadyToPayRequest {
    private JSONObject json;

    public static IsReadyToPayRequest fromJson(String json) throws Exception {
        return new IsReadyToPayRequest();
    }

    public JSONObject toJson() { return new JSONObject(); }
}
