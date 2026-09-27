package com.tech.ayugram.stripe.stub.net;

import com.tech.ayugram.stripe.stub.model.Token;
import org.json.JSONException;
import org.json.JSONObject;

public class TokenParser {
    public static Token parse(String json) throws org.json.JSONException {
        return new Token();
    }

    public static Token parse(JSONObject jsonObject) {
        return new Token();
    }
}