package com.google.android.gms.auth.api.signin;

public class GoogleSignInOptions {
    public static class Builder {
        public Builder() {}
        public Builder requestIdToken(String serverClientId) { return this; }
        public Builder requestEmail() { return this; }
        public Builder requestProfile() { return this; }
        public GoogleSignInOptions build() { return new GoogleSignInOptions(); }
    }
}