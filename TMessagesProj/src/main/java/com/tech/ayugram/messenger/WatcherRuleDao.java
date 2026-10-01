package com.tech.ayugram.messenger;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface WatcherRuleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(WatcherRule rule);
    
    @Update
    int update(WatcherRule rule);
    
    @Query("SELECT * FROM watcher_rules WHERE id = :id")
    WatcherRule getRule(String id);
    
    @Query("SELECT * FROM watcher_rules WHERE user_id = :userId AND enabled = 1")
    List<WatcherRule> getEnabledRules(long userId);
    
    @Query("SELECT * FROM watcher_rules WHERE user_id = :userId")
    LiveData<List<WatcherRule>> getAllRules(long userId);
    
    @Query("DELETE FROM watcher_rules WHERE id = :id")
    int deleteRule(String id);
    
    @Query("DELETE FROM watcher_rules WHERE user_id = :userId")
    int deleteAllForUser(long userId);
}