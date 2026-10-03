package com.google.android.gms.cast;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tasks.Task;

public class CastSession {
    public void sendMessage(String namespace, String message, ResultCallback<Status> callback) {
    }

    public RemoteMediaClient getRemoteMediaClient() {
        return null;
    }

    public void endSession(boolean stopCasting) {
    }
}