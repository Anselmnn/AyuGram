package com.tech.ayugram.play.stub;

public class IntegrityTokenRequest {
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

    public String getNonce() { return nonce; }
    public long getCloudProjectNumber() { return cloudProjectNumber; }
}