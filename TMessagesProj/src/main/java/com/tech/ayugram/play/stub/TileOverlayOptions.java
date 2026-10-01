package com.tech.ayugram.play.stub;

public final class TileOverlayOptions {
    private TileProvider tileProvider;
    private float zIndex = 0.0f;
    private boolean visible = true;
    private float transparency = 0.0f;
    private boolean fadeIn = true;
    private Object tag;

    public TileOverlayOptions tileProvider(TileProvider tileProvider) {
        this.tileProvider = tileProvider;
        return this;
    }

    public TileProvider getTileProvider() {
        return tileProvider;
    }

    public TileOverlayOptions zIndex(float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public float getZIndex() {
        return zIndex;
    }

    public TileOverlayOptions visible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public boolean isVisible() {
        return visible;
    }

    public TileOverlayOptions transparency(float transparency) {
        this.transparency = transparency;
        return this;
    }

    public float getTransparency() {
        return transparency;
    }

    public TileOverlayOptions fadeIn(boolean fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    public boolean getFadeIn() {
        return fadeIn;
    }

    public TileOverlayOptions tag(Object tag) {
        this.tag = tag;
        return this;
    }

    public Object getTag() {
        return tag;
    }

    public interface TileProvider {
        Tile getTile(int x, int y, int zoom);
    }

    public static final class Tile {
        public final int width;
        public final int height;
        public final byte[] data;

        public Tile(int width, int height, byte[] data) {
            this.width = width;
            this.height = height;
            this.data = data;
        }

        public static final Tile NO_TILE = new Tile(-1, -1, null);
    }
}