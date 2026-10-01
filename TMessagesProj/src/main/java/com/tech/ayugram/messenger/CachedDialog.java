package com.tech.ayugram.messenger;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cached_dialogs",
    primaryKeys = {"user_id", "folder_id"})
public class CachedDialog {
    @NonNull
    public long user_id;
    
    @NonNull
    public int folder_id;
    
    public byte[] raw_data;
    public long updated_at;
}