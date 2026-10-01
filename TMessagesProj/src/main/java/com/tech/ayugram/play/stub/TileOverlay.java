package com.tech.ayugram.play.stub;

public final class TileOverlay {
    private String id;
    private TileProvider tileProvider;
    private float zIndex;
    private boolean visible;
    private float transparency;
    private boolean fadeIn;
    private Object tag;

    TileOverlay(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public TileProvider getTileProvider() {
        return tileProvider;
    }

    public void setTileProvider(TileProvider tileProvider) {
        this.tileProvider = tileProvider;
    }

    public float getZIndex() {
        return zIndex;
    }

    public void setZIndex(float zIndex) {
        this.zIndex = zIndex;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public float getTransparency() {
        return transparency;
    }

    public void setTransparency(float transparency) {
        this.transparency = transparency;
    }

    public boolean getFadeIn() {
        return fadeIn;
    }

    public void setFadeIn(boolean fadeIn) {
        this.fadeIn = fadeIn;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public void remove() {
    }

    public void clearTileCache() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TileOverlay that = (TileOverlay) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}