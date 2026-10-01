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
public interface FolderConfigDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(FolderConfig config);
    
    @Update
    int update(FolderConfig config);
    
    @Query("SELECT * FROM folder_configs WHERE id = :id")
    FolderConfig getConfig(String id);
    
    @Query("SELECT * FROM folder_configs WHERE user_id = :userId ORDER BY order ASC")
    LiveData<List<FolderConfig>> getAllConfigs(long userId);
    
    @Query("SELECT * FROM folder_configs WHERE user_id = :userId AND is_custom = 1")
    List<FolderConfig> getCustomConfigs(long userId);
    
    @Query("DELETE FROM folder_configs WHERE id = :id")
    int deleteConfig(String id);
    
    @Query("DELETE FROM folder_configs WHERE user_id = :userId")
    int deleteAllForUser(long userId);
}