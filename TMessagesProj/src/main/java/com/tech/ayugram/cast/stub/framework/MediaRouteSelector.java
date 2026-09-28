package com.tech.ayugram.cast.stub.framework;

public class MediaRouteSelector {
    private MediaRouteSelector() {}

    public static class Builder {
        public Builder addControlCategory(String category) { return this; }
        public MediaRouteSelector build() { return new MediaRouteSelector(); }
    }
}