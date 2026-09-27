package com.tech.ayugram.maps.stub;

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