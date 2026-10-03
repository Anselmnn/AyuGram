package com.tech.ayugram.play.stub;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;

public class FusedLocationProviderClient {
    public Task<Location> getLastLocation() {
        return com.google.android.gms.tasks.Tasks.forResult(null);
    }

    public Task<Void> requestLocationUpdates(LocationRequest request, LocationCallback callback, android.os.Looper looper) {
        return com.google.android.gms.tasks.Tasks.forResult(null);
    }

    public Task<Void> removeLocationUpdates(LocationCallback callback) {
        return com.google.android.gms.tasks.Tasks.forResult(null);
    }
}