package com.tech.ayugram.play.stub;

import android.location.Location;

import java.util.List;

public class LocationResult {
    private List<Location> locations;

    public LocationResult(List<Location> locations) {
        this.locations = locations;
    }

    public static LocationResult create(List<Location> locations) {
        return new LocationResult(locations);
    }

    public Location getLastLocation() {
        if (locations != null && !locations.isEmpty()) {
            return locations.get(locations.size() - 1);
        }
        return null;
    }

    public List<Location> getLocations() {
        return locations;
    }
}