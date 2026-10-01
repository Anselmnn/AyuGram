package com.tech.ayugram.messenger;

import android.content.Context;

import androidx.annotation.Keep;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.tech.ayugram.ApplicationLoader;

@Keep
@Database(entities = {
    LocalMessage.class,
    CachedDialog.class,
    WatcherRule.class,
    UserWatcher.class,
    FolderConfig.class
}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AyuDatabase extends RoomDatabase {
    private static AyuDatabase instance;
    
    public abstract LocalMessageDao localMessageDao();
    public abstract CachedDialogDao cachedDialogDao();
    public abstract WatcherRuleDao watcherRuleDao();
    public abstract UserWatcherDao userWatcherDao();
    public abstract FolderConfigDao folderConfigDao();

    public static synchronized AyuDatabase getInstance() {
        if (instance == null) {
            Context context = ApplicationLoader.getApplicationContext();
            instance = Room.databaseBuilder(context, AyuDatabase.class, "ayugram.db")
                .fallbackToDestructiveMigration()
                .build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}