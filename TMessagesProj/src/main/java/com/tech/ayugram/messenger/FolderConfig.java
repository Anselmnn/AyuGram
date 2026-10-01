package com.tech.ayugram.messenger;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "folder_configs")
public class FolderConfig {
    @PrimaryKey
    @NonNull
    public String id; // UUID
    
    public long user_id;
    public int folder_id;
    public String title = "";
    public String emoticon = "";
    public String flags_json = ""; // JSON with boolean flags
    public String included_peers_json = ""; // JSON array of longs
    public String excluded_peers_json = ""; // JSON array of longs
    public int order = 0;
    public long updated_at = 0;
    public boolean is_custom = true;
}