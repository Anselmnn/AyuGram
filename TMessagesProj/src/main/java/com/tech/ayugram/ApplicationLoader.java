package com.tech.ayugram;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.Keep;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.room.Room;
import androidx.startup.Initializer;
import androidx.startup.StartupLogger;

import com.tech.ayugram.messenger.AyuDatabase;
import com.tech.ayugram.messenger.ContactsController;
import com.tech.ayugram.messenger.DownloadController;
import com.tech.ayugram.messenger.FileLoader;
import com.tech.ayugram.messenger.ImageLoader;
import com.tech.ayugram.messenger.MediaController;
import com.tech.ayugram.messenger.MessagesController;
import com.tech.ayugram.messenger.NotificationsController;
import com.tech.ayugram.messenger.SendMessagesHelper;
import com.tech.ayugram.messenger.UserConfig;
import com.tech.ayugram.tgnet.ConnectionsManager;
import com.tech.ayugram.ui.LaunchActivity;

import java.util.List;

@Keep
public class ApplicationLoader extends Application {
    private static ApplicationLoader instance;
    private static Context applicationContext;
    private static SharedPreferences preferences;
    private static AyuDatabase ayuDatabase;
    
    // Build config
    public static final String APP_PACKAGE = "com.tech.ayugram";
    public static final String APP_NAME = "AyuGram";
    public static final int APP_VERSION_CODE = 1;
    public static final String APP_VERSION_NAME = "1.0.0";
    public static final int APP_API_ID = 6;
    public static final String APP_API_HASH = "eb06d4abfb49dc3eeb1aeb98ae0f581e";
    
    // Device info (R13)
    public static final String DEVICE_MODEL = "samsung SM-S911B";
    public static final String DEVICE_SDK = "34";
    public static final String DEVICE_FINGERPRINT = "samsung/sm-s911b/sm-s911b:14/UP1A.231005.007/S911BXXS7CXH1:user/release-keys";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = getApplicationContext();
        
        // Initialize preferences
        preferences = getSharedPreferences("ayugram_prefs", Context.MODE_PRIVATE);
        
        // Initialize Room database
        ayuDatabase = Room.databaseBuilder(this, AyuDatabase.class, "ayugram.db")
            .fallbackToDestructiveMigration()
            .build();
        
        // Initialize core components
        initializeCore();
        
        // Initialize FCM (R16)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        }
    }
    
    private void initializeCore() {
        // Initialize UserConfig first
        UserConfig.getInstance(0).loadConfig();
        
        // Initialize connections
        ConnectionsManager.getInstance(0).setDeviceInfo(
            DEVICE_MODEL,
            DEVICE_SDK,
            APP_VERSION_NAME,
            DEVICE_FINGERPRINT
        );
        
        // Initialize other components
        FileLoader.getInstance(0);
        ImageLoader.getInstance(0);
        MediaController.getInstance(0);
        MessagesController.getInstance(0);
        NotificationsController.getInstance(0);
        SendMessagesHelper.getInstance(0);
        ContactsController.getInstance(0);
        DownloadController.getInstance(0);
    }

    public static ApplicationLoader getInstance() {
        return instance;
    }

    public static Context getApplicationContext() {
        return applicationContext;
    }

    public static SharedPreferences getPreferences() {
        return preferences;
    }

    public static AyuDatabase getAyuDatabase() {
        return ayuDatabase;
    }

    public static void postInitApplication() {
        // Called after UI is ready
    }
}