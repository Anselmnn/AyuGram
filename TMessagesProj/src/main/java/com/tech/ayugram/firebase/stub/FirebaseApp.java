package com.tech.ayugram.firebase.stub;

import android.content.Context;

public class FirebaseApp {
    private static FirebaseApp instance;

    private FirebaseApp() {}

    public static FirebaseApp getInstance() {
        if (instance == null) {
            instance = new FirebaseApp();
        }
        return instance;
    }

    public static FirebaseApp initializeApp(Context context) {
        if (instance == null) {
            instance = new FirebaseApp();
        }
        return instance;
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions options) {
        if (instance == null) {
            instance = new FirebaseApp();
        }
        return instance;
    }

    public String getName() { return "[DEFAULT]"; }
    public String getOptions() { return ""; }
}