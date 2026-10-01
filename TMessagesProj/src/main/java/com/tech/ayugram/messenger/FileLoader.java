package com.tech.ayugram.messenger;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

@Keep
public class FileLoader {
    private static FileLoader[] instances = new FileLoader[3];
    
    private final ConcurrentHashMap<String, Object> loadingFiles = new ConcurrentHashMap<>();
    private File cacheDir;
    private File mediaDir;
    private File documentsDir;

    private FileLoader(int accountNum) {
        Context context = ApplicationLoader.getApplicationContext();
        cacheDir = context.getCacheDir();
        mediaDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Telegram");
        documentsDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "Telegram");
        
        if (!mediaDir.exists()) mediaDir.mkdirs();
        if (!documentsDir.exists()) documentsDir.mkdirs();
    }

    public static FileLoader getInstance(int num) {
        if (instances[num] == null) {
            instances[num] = new FileLoader(num);
        }
        return instances[num];
    }

    public static FileLoader getInstance() {
        return getInstance(0);
    }

    public File getDirectory(int type) {
        switch (type) {
            case 0: return cacheDir;
            case 1: return mediaDir;
            case 2: return documentsDir;
            default: return cacheDir;
        }
    }

    public String getAttachFileName(String prefix) {
        return prefix + System.currentTimeMillis() + ".tmp";
    }

    public File getPathToAttach(String name, boolean cache) {
        File dir = cache ? cacheDir : mediaDir;
        return new File(dir, name);
    }

    public boolean isLoadingFile(String name) {
        return loadingFiles.containsKey(name);
    }

    public void setLoadingFile(String name, boolean loading) {
        if (loading) {
            loadingFiles.put(name, new Object());
        } else {
            loadingFiles.remove(name);
        }
    }

    public void cleanUp() {
        // Clean old files
    }
}