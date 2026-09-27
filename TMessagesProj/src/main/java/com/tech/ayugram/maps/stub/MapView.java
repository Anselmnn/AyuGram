package com.tech.ayugram.maps.stub;

import android.content.Context;
import android.os.Bundle;

public class MapView extends android.view.View {
    public MapView(Context context) {
        super(context);
    }

    public MapView(Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    public MapView(Context context, android.util.AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onCreate(Bundle savedInstanceState) {}
    public void onStart() {}
    public void onResume() {}
    public void onPause() {}
    public void onStop() {}
    public void onDestroy() {}
    public void onSaveInstanceState(Bundle outState) {}
    public void onLowMemory() {}

    public void getMapAsync(OnMapReadyCallback callback) {}

    public interface OnMapReadyCallback {
        void onMapReady(GoogleMap googleMap);
    }
}