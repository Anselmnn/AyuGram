package com.google.android.gms.auth.api.signin;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.tasks.Task;

public class GoogleSignInClient {
    private Context context;
    private GoogleSignInOptions options;

    public GoogleSignInClient(Context context, GoogleSignInOptions options) {
        this.context = context;
        this.options = options;
    }

    public Intent getSignInIntent() {
        return new Intent();
    }

    public Task<GoogleSignInAccount> silentSignIn() {
        return null;
    }

    public Task<Void> signOut() {
        return null;
    }

    public Task<Void> revokeAccess() {
        return null;
    }
}