package com.tech.ayugram.play.stub;

import android.content.Intent;
import com.tech.ayugram.play.stub.Task;

public class GoogleSignInClient {
    public Task<GoogleSignInAccount> silentSignIn() {
        return Task.forResult(null);
    }

    public Task<GoogleSignInAccount> signIn() {
        return Task.forResult(null);
    }

    public Intent getSignInIntent() {
        return new Intent();
    }

    public Task<Void> signOut() {
        return Task.forResult(null);
    }

    public Task<Void> revokeAccess() {
        return Task.forResult(null);
    }
}