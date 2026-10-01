package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable;

public class GoogleSignInOptions implements Parcelable {
    public static final int DEFAULT_SIGN_IN = 0;
    public static final int SIGN_IN_MODE_OPTIONAL = 1;
    public static final int SIGN_IN_MODE_REQUIRED = 2;

    private int signInMode;
    private boolean requestEmail;
    private boolean requestProfile;
    private boolean requestIdToken;
    private String serverClientId;
    private boolean requestAuthCode;
    private boolean forceCodeForRefreshToken;
    private String[] scopes;

    private GoogleSignInOptions(Builder builder) {
        this.signInMode = builder.signInMode;
        this.requestEmail = builder.requestEmail;
        this.requestProfile = builder.requestProfile;
        this.requestIdToken = builder.requestIdToken;
        this.serverClientId = builder.serverClientId;
        this.requestAuthCode = builder.requestAuthCode;
        this.forceCodeForRefreshToken = builder.forceCodeForRefreshToken;
        this.scopes = builder.scopes;
    }

    public int getSignInMode() {
        return signInMode;
    }

    public boolean isRequestEmail() {
        return requestEmail;
    }

    public boolean isRequestProfile() {
        return requestProfile;
    }

    public boolean isRequestIdToken() {
        return requestIdToken;
    }

    public String getServerClientId() {
        return serverClientId;
    }

    public boolean isRequestAuthCode() {
        return requestAuthCode;
    }

    public boolean isForceCodeForRefreshToken() {
        return forceCodeForRefreshToken;
    }

    public String[] getScopes() {
        return scopes;
    }

    public static final class Builder {
        private int signInMode = DEFAULT_SIGN_IN;
        private boolean requestEmail = false;
        private boolean requestProfile = true;
        private boolean requestIdToken = false;
        private String serverClientId;
        private boolean requestAuthCode = false;
        private boolean forceCodeForRefreshToken = false;
        private String[] scopes;

        public Builder() {
        }

        public Builder(GoogleSignInOptions options) {
            this.signInMode = options.signInMode;
            this.requestEmail = options.requestEmail;
            this.requestProfile = options.requestProfile;
            this.requestIdToken = options.requestIdToken;
            this.serverClientId = options.serverClientId;
            this.requestAuthCode = options.requestAuthCode;
            this.forceCodeForRefreshToken = options.forceCodeForRefreshToken;
            this.scopes = options.scopes;
        }

        public Builder requestEmail() {
            this.requestEmail = true;
            return this;
        }

        public Builder requestProfile() {
            this.requestProfile = true;
            return this;
        }

        public Builder requestIdToken(String serverClientId) {
            this.requestIdToken = true;
            this.serverClientId = serverClientId;
            return this;
        }

        public Builder requestServerAuthCode(String serverClientId) {
            this.requestAuthCode = true;
            this.serverClientId = serverClientId;
            return this;
        }

        public Builder requestScopes(String scope, String... scopes) {
            java.util.ArrayList<String> scopeList = new java.util.ArrayList<>();
            scopeList.add(scope);
            if (scopes != null) {
                for (String s : scopes) {
                    scopeList.add(s);
                }
            }
            this.scopes = scopeList.toArray(new String[0]);
            return this;
        }

        public GoogleSignInOptions build() {
            return new GoogleSignInOptions(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new Parcelable.Creator<GoogleSignInOptions>() {
        @Override
        public GoogleSignInOptions createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public GoogleSignInOptions[] newArray(int size) {
            return new GoogleSignInOptions[size];
        }
    };
}