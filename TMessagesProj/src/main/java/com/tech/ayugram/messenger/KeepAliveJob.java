package com.tech.ayugram.messenger;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.tgnet.ConnectionsManager;

@Keep
public class KeepAliveJob extends JobService {
    private static final long KEEP_ALIVE_INTERVAL = 15 * 60 * 1000; // 15 minutes
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable keepAliveRunnable;

    @Override
    public boolean onStartJob(JobParameters params) {
        keepAliveRunnable = () -> {
            // Ping server to keep connection alive
            ConnectionsManager.getInstance().sendRequest(
                new com.tech.ayugram.tgnet.TLObject() {
                    @Override
                    public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
                },
                (response, error) -> {
                    // Reschedule
                    handler.postDelayed(keepAliveRunnable, KEEP_ALIVE_INTERVAL);
                }
            );
            return true;
        };
        
        handler.post(keepAliveRunnable);
        return true; // Work is ongoing
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        handler.removeCallbacks(keepAliveRunnable);
        return true; // Reschedule
    }
}