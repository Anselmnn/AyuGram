package com.tech.ayugram.messenger;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "local_messages",
    primaryKeys = {"user_id", "dialog_id", "message_id"},
    indices = {
        @androidx.room.Index(value = {"user_id", "dialog_id", "topic_id", "date"}, name = "idx_local_messages_dialog_date"),
        @androidx.room.Index(value = {"user_id", "dialog_id", "from_id", "date"}, name = "idx_local_messages_from"),
        @androidx.room.Index(value = {"user_id", "dialog_id", "text"}, name = "idx_local_messages_text")
    })
public class LocalMessage {
    @NonNull
    public long user_id;
    
    @NonNull
    public long dialog_id;
    
    @NonNull
    public long message_id;
    
    public long topic_id = 0;
    public long from_id;
    public int date;
    public String text = "";
    public byte[] raw_data;
    public boolean is_outgoing = false;
    public boolean is_read = false;
    public int views = 0;
    public int forwards = 0;
    public int edit_date = 0;
    public String reply_to_msg_id = "";
    public String media_type = "";
    public String media_path = "";
}