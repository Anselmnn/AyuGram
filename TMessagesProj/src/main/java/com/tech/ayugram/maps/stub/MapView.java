package com.tech.ayugram.maps.stub;

/**
 * Phase 2: Stub for com.tech.ayugram.maps.stub.MapView
 * Google Maps dependency removed in Phase 2
 */
public class MapView extends android.view.View {
    public MapView(android.content.Context context) {
        super(context);
    }

    public MapView(android.content.Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    public MapView(android.content.Context context, android.util.AttributeSet attrs, int defStyle) {
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