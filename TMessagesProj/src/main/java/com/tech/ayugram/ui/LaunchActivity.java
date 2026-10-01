package com.tech.ayugram.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.UserConfig;

@Keep
public class LaunchActivity extends AppCompatActivity {
    private static final int LAUNCH_DELAY = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        
        // Check if user is logged in
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (UserConfig.getInstance().registered && UserConfig.getInstance().userId != 0) {
                // Go to main dialogs
                startActivity(new Intent(this, DialogsActivity.class));
            } else {
                // Go to login/registration
                startActivity(new Intent(this, LoginActivity.class));
            }
            finish();
        }, LAUNCH_DELAY);
    }
}