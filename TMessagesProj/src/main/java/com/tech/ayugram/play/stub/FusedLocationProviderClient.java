package com.tech.ayugram.play.stub;

import android.location.Location;
import com.tech.ayugram.play.stub.Task;

public class FusedLocationProviderClient {
    public Task<Location> getLastLocation() { return Task.forResult(null); }
    public Task<Void> requestLocationUpdates(LocationRequest request, android.app.PendingIntent callbackIntent) { return Task.forResult(null); }
    public Task<Void> removeLocationUpdates(android.app.PendingIntent callbackIntent) { return Task.forResult(null); }
}