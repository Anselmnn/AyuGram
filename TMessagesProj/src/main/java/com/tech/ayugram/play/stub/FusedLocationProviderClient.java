package com.tech.ayugram.play.stub;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.tasks.Task;

public class FusedLocationProviderClient {
    public Task<Location> getLastLocation() {
        return com.google.android.gms.tasks.Tasks.forResult(null);
    }

    public Task<Void> requestLocationUpdates(android.location.LocationRequest request, android.location.LocationCallback callback, android.os.Looper looper) {
        return com.google.android.gms.tasks.Tasks.forResult(null);
    }

    public Task<Void> removeLocationUpdates(android.location.LocationCallback callback) {
        return com.google.android.gms.tasks.Tasks.forResult(null);
    }
}