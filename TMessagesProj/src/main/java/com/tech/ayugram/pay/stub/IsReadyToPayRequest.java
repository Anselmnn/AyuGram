package com.tech.ayugram.pay.stub;

import org.json.JSONException;
import org.json.JSONObject;

public class IsReadyToPayRequest {
    public static IsReadyToPayRequest fromJson(String json) throws org.json.JSONException {
        return new IsReadyToPayRequest();
    }

    public static IsReadyToPayRequest fromJson(JSONObject json) {
        return new IsReadyToPayRequest();
    }
}