package com.tech.ayugram.play.stub;

import android.content.Context;

/**
 * Phase 2: Stub for com.google.android.play.core.integrity.IntegrityManager
 * Google Play Integrity dependency removed in Phase 2
 */
public class IntegrityManager {
    private IntegrityManager() {}

    public static class IntegrityManagerFactory {
        public IntegrityManager create(Context context) {
            return new IntegrityManager();
        }
    }

    public static class IntegrityTokenRequest {
        private String nonce;
        private long cloudProjectNumber;

        private IntegrityTokenRequest() {}

        public static class Builder {
            private String nonce;
            private long cloudProjectNumber;

            public Builder setNonce(String nonce) {
                this.nonce = nonce;
                return this;
            }

            public Builder setCloudProjectNumber(long cloudProjectNumber) {
                this.cloudProjectNumber = cloudProjectNumber;
                return this;
            }

            public IntegrityTokenRequest build() {
                return new IntegrityTokenRequest();
            }
        }

        public static Builder builder() {
            return new Builder();
        }

        public String getNonce() {
            return nonce;
        }

        public long getCloudProjectNumber() {
            return cloudProjectNumber;
        }
    }

    public static class IntegrityTokenResponse {
        private String token;

        public String token() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public com.tech.ayugram.play.stub.Task<IntegrityTokenResponse> requestIntegrityToken(IntegrityTokenRequest request) {
        return com.tech.ayugram.play.stub.Task.forResult(new IntegrityTokenResponse());
    }
}