package com.tech.ayugram.play.stub;

import android.location.Location;

public abstract class LocationCallback {
    public void onLocationResult(LocationResult locationResult) {}
    public void onLocationAvailability(LocationAvailability locationAvailability) {}
}