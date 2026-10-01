package com.tech.ayugram.play.stub;

import android.graphics.Point;
import android.graphics.Rect;

public final class Projection {
    public LatLng fromScreenLocation(Point point) {
        return new LatLng(0, 0);
    }

    public Point toScreenLocation(LatLng latLng) {
        return new Point(0, 0);
    }

    public VisibleRegion getVisibleRegion() {
        return new VisibleRegion(
            new LatLng(0, 0),
            new LatLng(0, 0),
            new LatLng(0, 0),
            new LatLng(0, 0),
            new Rect(0, 0, 0, 0)
        );
    }

    public static final class VisibleRegion {
        public final LatLng farLeft;
        public final LatLng farRight;
        public final LatLng nearLeft;
        public final LatLng nearRight;
        public final Rect latLngBounds;

        public VisibleRegion(LatLng farLeft, LatLng farRight, LatLng nearLeft, LatLng nearRight, Rect latLngBounds) {
            this.farLeft = farLeft;
            this.farRight = farRight;
            this.nearLeft = nearLeft;
            this.nearRight = nearRight;
            this.latLngBounds = latLngBounds;
        }
    }
}