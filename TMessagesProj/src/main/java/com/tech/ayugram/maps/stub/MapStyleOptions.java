package com.tech.ayugram.maps.stub;

/**
 * Phase 2: Stub for com.google.android.gms.maps.model.MapStyleOptions
 * Google Maps dependency removed in Phase 2
 */
public class MapStyleOptions {
    private String jsonStyle;

    private MapStyleOptions(String jsonStyle) {
        this.jsonStyle = jsonStyle;
    }

    public static MapStyleOptions loadRawResourceStyle(android.content.Context context, int resourceId) {
        return new MapStyleOptions("");
    }

    public static MapStyleOptions newInstance(String jsonStyle) {
        return new MapStyleOptions(jsonStyle);
    }

    public String getJsonStyle() { return jsonStyle; }
}