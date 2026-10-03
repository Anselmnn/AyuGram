package com.tech.ayugram.messenger;

import androidx.lifecycle.LiveData;

public class FolderSyncManager {
    public interface FolderSyncCallback {
        void onSyncComplete();
        void onSyncError(Exception e);
    }

    public void startSync() {}
    public void stopSync() {}
    public void backupToCloud(FolderSyncCallback callback) {}
    public void restoreFromCloud(FolderSyncCallback callback) {}
}