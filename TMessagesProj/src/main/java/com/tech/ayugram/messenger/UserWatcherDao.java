package com.tech.ayugram.messenger;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface UserWatcherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(UserWatcher watcher);
    
    @Query("SELECT * FROM user_watchers WHERE user_id = :userId AND watched_user_id = :watchedUserId")
    UserWatcher getWatcher(long userId, long watchedUserId);
    
    @Query("SELECT * FROM user_watchers WHERE user_id = :userId")
    LiveData<List<UserWatcher>> getAllWatchers(long userId);
    
    @Query("DELETE FROM user_watchers WHERE user_id = :userId AND watched_user_id = :watchedUserId")
    int deleteWatcher(long userId, long watchedUserId);
    
    @Query("DELETE FROM user_watchers WHERE user_id = :userId")
    int deleteAllForUser(long userId);
}