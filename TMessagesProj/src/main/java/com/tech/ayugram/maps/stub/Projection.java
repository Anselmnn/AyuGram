package com.tech.ayugram.maps.stub;

import com.tech.ayugram.maps.stub.model.LatLng;
import com.tech.ayugram.maps.stub.model.LatLngBounds;

public class Projection {
    public LatLng fromScreenLocation(android.graphics.Point point) {
        return new LatLng(0, 0);
    }

    public android.graphics.Point toScreenLocation(LatLng latLng) {
        return new android.graphics.Point(0, 0);
    }

    public LatLngBounds getVisibleRegion() {
        return new LatLngBounds(new LatLng(0, 0), new LatLng(0, 0));
    }
}