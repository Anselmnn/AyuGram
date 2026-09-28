package com.tech.ayugram.play.stub;

import com.tech.ayugram.play.stub.Task;

public class SmsRetrieverClient {
    public Task<Void> startSmsRetriever() {
        return Task.forResult(null);
    }
}