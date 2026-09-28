package com.tech.ayugram.play.stub;

import android.content.Context;

public class SmsRetriever {
    public static SmsRetrieverClient getClient(Context context) {
        return new SmsRetrieverClient();
    }
}