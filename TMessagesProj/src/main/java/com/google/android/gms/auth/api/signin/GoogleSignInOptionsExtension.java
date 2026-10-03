package com.google.android.gms.auth.api.signin;

public class GoogleSignInOptionsExtension {
    public static class Builder {
        public Builder() {}
        public Builder requestServerAuthCode(String serverClientId) { return this; }
        public GoogleSignInOptions build() { return new GoogleSignInOptions(); }
    }
}