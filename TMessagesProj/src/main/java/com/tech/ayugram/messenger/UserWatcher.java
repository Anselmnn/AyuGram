package com.tech.ayugram.messenger;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_watchers",
    primaryKeys = {"user_id", "watched_user_id"})
public class UserWatcher {
    @NonNull
    public long user_id; // Current user account
    
    @NonNull
    public long watched_user_id; // User being watched
    
    public long added_at = 0;
    public String note = "";
}