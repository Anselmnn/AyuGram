package com.tech.ayugram.play.stub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Phase 2: Stub for com.google.android.gms.auth.api.signin.GoogleSignIn
 * Google Sign-In dependency removed in Phase 2
 */
public class GoogleSignIn {
    private GoogleSignIn() {}

    public static GoogleSignInClient getClient(Activity activity, GoogleSignInOptions options) {
        return new GoogleSignInClient();
    }

    public static GoogleSignInClient getClient(Context context, GoogleSignInOptions options) {
        return new GoogleSignInClient();
    }

    public static GoogleSignInAccount getLastSignedInAccount(Context context) {
        return null;
    }

    public static class GoogleSignInClient {
        public com.tech.ayugram.play.stub.Task<GoogleSignInAccount> silentSignIn() {
            return com.tech.ayugram.play.stub.Task.forResult(null);
        }

        public com.tech.ayugram.play.stub.Task<GoogleSignInAccount> signIn() {
            return com.tech.ayugram.play.stub.Task.forResult(null);
        }

        public Intent getSignInIntent() {
            return new Intent();
        }

        public com.tech.ayugram.play.stub.Task<Void> signOut() {
            return com.tech.ayugram.play.stub.Task.forResult(null);
        }

        public com.tech.ayugram.play.stub.Task<Void> revokeAccess() {
            return com.tech.ayugram.play.stub.Task.forResult(null);
        }
    }

    public static class GoogleSignInAccount {
        private String id;
        private String email;
        private String displayName;
        private String givenName;
        private String familyName;
        private android.net.Uri photoUrl;
        private String idToken;
        private String serverAuthCode;

        public String getId() { return id; }
        public String getEmail() { return email; }
        public String getDisplayName() { return displayName; }
        public String getGivenName() { return givenName; }
        public String getFamilyName() { return familyName; }
        public android.net.Uri getPhotoUrl() { return photoUrl; }
        public String getIdToken() { return idToken; }
        public String getServerAuthCode() { return serverAuthCode; }
    }

    public static class GoogleSignInOptions {
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
}