package com.tech.ayugram.play.stub;

import android.content.Context;

public class SafetyNet {
    private SafetyNet() {}

    public static SafetyNetClient getClient(Context context) {
        return new SafetyNetClient();
    }

    public static class SafetyNetClient {
        public com.tech.ayugram.play.stub.Task<AttestationResponse> attest(byte[] nonce, String apiKey) {
            return com.tech.ayugram.play.stub.Task.forResult(new AttestationResponse());
        }
    }

    public static class AttestationResponse {
        private String jwsResult;

        public String getJwsResult() { return jwsResult; }
        public void setJwsResult(String jwsResult) { this.jwsResult = jwsResult; }
    }
}