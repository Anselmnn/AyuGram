package com.tech.ayugram.messenger;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface LocalMessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(LocalMessage message);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<LocalMessage> messages);
    
    @Query("SELECT * FROM local_messages WHERE user_id = :userId AND dialog_id = :dialogId AND topic_id = :topicId AND message_id < :offsetId ORDER BY date DESC LIMIT :limit")
    List<LocalMessage> getMessagesForDialog(long userId, long dialogId, long topicId, long offsetId, int limit);
    
    @Query("SELECT * FROM local_messages WHERE user_id = :userId AND dialog_id = :dialogId AND topic_id = :topicId ORDER BY date DESC LIMIT :limit")
    List<LocalMessage> getRecentMessages(long userId, long dialogId, long topicId, int limit);
    
    @Query("SELECT * FROM local_messages WHERE user_id = :userId AND dialog_id = :dialogId AND from_id = :fromId ORDER BY date DESC LIMIT :limit")
    List<LocalMessage> getMessagesByAuthor(long userId, long dialogId, long fromId, int limit);
    
    @Query("SELECT * FROM local_messages WHERE user_id = :userId AND dialog_id = :dialogId AND text LIKE :pattern ORDER BY date DESC LIMIT :limit")
    List<LocalMessage> searchMessages(long userId, long dialogId, String pattern, int limit);
    
    @Query("SELECT * FROM local_messages WHERE user_id = :userId ORDER BY date DESC LIMIT :limit")
    List<LocalMessage> getAllMessages(long userId, int limit);
    
    @Query("DELETE FROM local_messages WHERE user_id = :userId AND dialog_id = :dialogId AND date < :beforeDate")
    int deleteOldMessages(long userId, long dialogId, int beforeDate);
    
    @Query("DELETE FROM local_messages WHERE user_id = :userId")
    int deleteAllForUser(long userId);
    
    @Delete
    int delete(LocalMessage message);
}