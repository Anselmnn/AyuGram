package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.Task;

public class IntegrityManager {
    private android.content.Context context;

    private IntegrityManager(android.content.Context context) {
        this.context = context;
    }

    public static IntegrityManager create(android.content.Context context) {
        return new IntegrityManager(context);
    }

    public Task<IntegrityTokenResponse> requestIntegrityToken(IntegrityTokenRequest request) {
        return null;
    }

    public static class IntegrityTokenRequest {
        private String cloudProjectNumber;
        private String nonce;
        private String requestHash;

        private IntegrityTokenRequest(Builder builder) {
            this.cloudProjectNumber = builder.cloudProjectNumber;
            this.nonce = builder.nonce;
            this.requestHash = builder.requestHash;
        }

        public String getCloudProjectNumber() {
            return cloudProjectNumber;
        }

        public String getNonce() {
            return nonce;
        }

        public String getRequestHash() {
            return requestHash;
        }

        public static class Builder {
            private String cloudProjectNumber;
            private String nonce;
            private String requestHash;

            public Builder() {
            }

            public Builder setCloudProjectNumber(String cloudProjectNumber) {
                this.cloudProjectNumber = cloudProjectNumber;
                return this;
            }

            public Builder setNonce(String nonce) {
                this.nonce = nonce;
                return this;
            }

            public Builder setRequestHash(String requestHash) {
                this.requestHash = requestHash;
                return this;
            }

            public IntegrityTokenRequest build() {
                return new IntegrityTokenRequest(this);
            }
        }
    }

    public static class IntegrityTokenResponse {
        private String token;

        public String getToken() {
            return token;
        }
    }
}