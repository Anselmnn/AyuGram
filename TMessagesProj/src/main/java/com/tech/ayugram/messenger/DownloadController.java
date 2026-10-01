package com.tech.ayugram.messenger;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.tgnet.ConnectionsManager;
import com.tech.ayugram.tgnet.RequestDelegate;
import com.tech.ayugram.tgnet.TLRPC;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Keep
public class DownloadController {
    private static DownloadController[] instances = new DownloadController[3];
    
    private final ConcurrentHashMap<String, FileDownload> activeDownloads = new ConcurrentHashMap<>();
    private final ExecutorService downloadExecutor = Executors.newFixedThreadPool(3);
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private DownloadController(int accountNum) {
    }

    public static DownloadController getInstance(int num) {
        if (instances[num] == null) {
            instances[num] = new DownloadController(num);
        }
        return instances[num];
    }

    public static DownloadController getInstance() {
        return getInstance(0);
    }

    public void downloadFile(String url, String fileName, FileDownloadCallback callback) {
        FileDownload download = new FileDownload(url, fileName, callback);
        activeDownloads.put(url, download);
        downloadExecutor.execute(download);
    }

    public void cancelDownload(String url) {
        FileDownload download = activeDownloads.remove(url);
        if (download != null) {
            download.cancel();
        }
    }

    public boolean isDownloading(String url) {
        return activeDownloads.containsKey(url);
    }

    public float getProgress(String url) {
        FileDownload download = activeDownloads.get(url);
        return download != null ? download.progress : 0f;
    }

    private class FileDownload implements Runnable {
        private final String url;
        private final String fileName;
        private final FileDownloadCallback callback;
        private volatile boolean cancelled = false;
        public volatile float progress = 0f;

        FileDownload(String url, String fileName, FileDownloadCallback callback) {
            this.url = url;
            this.fileName = fileName;
            this.callback = callback;
        }

        @Override
        public void run() {
            try {
                URL downloadUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
                connection.setConnectTimeout(30000);
                connection.setReadTimeout(60000);
                
                int fileSize = connection.getContentLength();
                InputStream input = connection.getInputStream();
                
                File outputFile = FileLoader.getInstance().getPathToAttach(fileName, false);
                FileOutputStream output = new FileOutputStream(outputFile);
                
                byte[] buffer = new byte[8192];
                int totalRead = 0;
                int read;
                
                while ((read = input.read(buffer)) != -1 && !cancelled) {
                    output.write(buffer, 0, read);
                    totalRead += read;
                    if (fileSize > 0) {
                        progress = (float) totalRead / fileSize;
                        notifyProgress();
                    }
                }
                
                output.close();
                input.close();
                
                if (!cancelled) {
                    mainHandler.post(() -> callback.onSuccess(outputFile));
                } else {
                    outputFile.delete();
                    mainHandler.post(() -> callback.onError(new Exception("Cancelled")));
                }
            } catch (Exception e) {
                if (!cancelled) {
                    mainHandler.post(() -> callback.onError(e));
                }
            } finally {
                activeDownloads.remove(url);
            }
        }

        void cancel() {
            cancelled = true;
        }

        void notifyProgress() {
            mainHandler.post(() -> callback.onProgress(progress));
        }
    }

    public interface FileDownloadCallback {
        void onSuccess(File file);
        void onError(Exception error);
        void onProgress(float progress);
    }

    public void cleanup() {
        for (FileDownload download : activeDownloads.values()) {
            download.cancel();
        }
        activeDownloads.clear();
    }
}