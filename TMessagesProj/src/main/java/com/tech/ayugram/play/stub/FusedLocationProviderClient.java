package com.tech.ayugram.play.stub;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.tasks.Task;

public class FusedLocationProviderClient {
    public Task<Location> getLastLocation() {
        return null;
    }

    public Task<Void> requestLocationUpdates(com.google.android.gms.location.LocationRequest request, com.google.android.gms.location.LocationCallback callback, android.os.Looper looper) {
        return null;
    }

    public Task<Void> removeLocationUpdates(com.google.android.gms.location.LocationCallback callback) {
        return null;
    }
}