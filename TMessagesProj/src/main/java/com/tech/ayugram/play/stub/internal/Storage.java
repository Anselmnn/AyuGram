package com.tech.ayugram.play.stub.internal;

public class Storage {
    public void putString(String key, String value) {}

    public String getString(String key, String defaultValue) {
        return defaultValue;
    }

    public void remove(String key) {}

    public void clear() {}
}