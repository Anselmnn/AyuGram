package com.tech.ayugram.play.stub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;

public class GoogleSignIn {
    private GoogleSignIn() {}

    @NonNull
    public static GoogleSignInClient getClient(@NonNull Activity activity, @NonNull GoogleSignInOptions options) {
        return new GoogleSignInClient();
    }

    @NonNull
    public static GoogleSignInClient getClient(@NonNull Context context, @NonNull GoogleSignInOptions options) {
        return new GoogleSignInClient();
    }

    @NonNull
    public static GoogleSignInAccount getLastSignedInAccount(@NonNull Context context) {
        return null;
    }

    @NonNull
    public static com.tech.ayugram.play.stub.Task<GoogleSignInAccount> getSignedInAccountFromIntent(@NonNull Intent intent) {
        return com.tech.ayugram.play.stub.Task.forResult(null);
    }

    public static final String VERSION = "2026.09.29.5";
}