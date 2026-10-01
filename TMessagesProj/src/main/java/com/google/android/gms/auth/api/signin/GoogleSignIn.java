package com.google.android.gms.auth.api.signin;

import android.content.Context;
import com.google.android.gms.tasks.Task;

public class GoogleSignIn {
    private static GoogleSignInClient client;

    private GoogleSignIn() {
    }

    public static GoogleSignInClient getClient(Context context, GoogleSignInOptions options) {
        if (client == null) {
            client = new GoogleSignInClient(context, options);
        }
        return client;
    }

    public static GoogleSignInClient getClient(android.app.Activity activity, GoogleSignInOptions options) {
        if (client == null) {
            client = new GoogleSignInClient(activity, options);
        }
        return client;
    }

    public static GoogleSignInAccount getLastSignedInAccount(Context context) {
        return null;
    }

    public static Task<GoogleSignInAccount> getSignedInAccountFromIntent(Intent intent) {
        return null;
    }
}