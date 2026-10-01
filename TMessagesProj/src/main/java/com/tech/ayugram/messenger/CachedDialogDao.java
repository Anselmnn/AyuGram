package com.tech.ayugram.messenger;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Delete;

@Dao
public interface CachedDialogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(CachedDialog dialog);
    
    @Query("SELECT * FROM cached_dialogs WHERE user_id = :userId AND folder_id = :folderId")
    CachedDialog getDialog(long userId, int folderId);
    
    @Query("SELECT * FROM cached_dialogs WHERE user_id = :userId")
    LiveData<List<CachedDialog>> getAllDialogs(long userId);
    
    @Query("DELETE FROM cached_dialogs WHERE user_id = :userId AND folder_id = :folderId")
    int deleteDialog(long userId, int folderId);
    
    @Query("DELETE FROM cached_dialogs WHERE user_id = :userId")
    int deleteAllForUser(long userId);
}