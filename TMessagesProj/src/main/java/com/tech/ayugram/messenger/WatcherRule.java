package com.tech.ayugram.messenger;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "watcher_rules")
public class WatcherRule {
    @PrimaryKey
    @NonNull
    public String id;
    
    public String title = "";
    public String regex_pattern = "";
    public String included_dialog_ids = ""; // JSON array
    public String excluded_dialog_ids = ""; // JSON array
    public boolean override_silent = false;
    public boolean forward_to_saved = false;
    public String webhook_url = "";
    public long created_at = 0;
    public boolean enabled = true;
}