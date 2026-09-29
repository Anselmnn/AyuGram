package com.tech.ayugram.play.stub;

public class GoogleSignInOptions {
    public static final int DEFAULT_SIGN_IN = 1;
    public static final int SIGN_IN = 2;

    private GoogleSignInOptions() {}

    public static class Builder {
        private boolean requestIdToken = false;
        private String serverClientId;
        private boolean requestEmail = false;
        private boolean requestProfile = false;
        private boolean requestId = false;

        public Builder() {}

        public Builder requestIdToken(String serverClientId) {
            this.requestIdToken = true;
            this.serverClientId = serverClientId;
            return this;
        }

        public Builder requestServerAuthCode(String serverClientId) {
            this.serverClientId = serverClientId;
            return this;
        }

        public Builder requestEmail() {
            this.requestEmail = true;
            return this;
        }

        public Builder requestProfile() {
            this.requestProfile = true;
            return this;
        }

        public Builder requestId() {
            this.requestId = true;
            return this;
        }

        public GoogleSignInOptions build() {
            return new GoogleSignInOptions();
        }
    }
}