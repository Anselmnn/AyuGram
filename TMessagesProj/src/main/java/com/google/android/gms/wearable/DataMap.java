package com.google.android.gms.wearable;

public class DataMap {
    private android.os.Bundle bundle;

    public DataMap() {
        this.bundle = new android.os.Bundle();
    }

    public void putString(String key, String value) {
        bundle.putString(key, value);
    }

    public String getString(String key) {
        return bundle.getString(key);
    }

    public void putInt(String key, int value) {
        bundle.putInt(key, value);
    }

    public int getInt(String key) {
        return bundle.getInt(key);
    }

    public void putLong(String key, long value) {
        bundle.putLong(key, value);
    }

    public long getLong(String key) {
        return bundle.getLong(key);
    }

    public void putDouble(String key, double value) {
        bundle.putDouble(key, value);
    }

    public double getDouble(String key) {
        return bundle.getDouble(key);
    }

    public void putFloat(String key, float value) {
        bundle.putFloat(key, value);
    }

    public float getFloat(String key) {
        return bundle.getFloat(key);
    }

    public void putBoolean(String key, boolean value) {
        bundle.putBoolean(key, value);
    }

    public boolean getBoolean(String key) {
        return bundle.getBoolean(key);
    }

    public void putByteArray(String key, byte[] value) {
        bundle.putByteArray(key, value);
    }

    public byte[] getByteArray(String key) {
        return bundle.getByteArray(key);
    }

    public void putStringArray(String key, String[] value) {
        bundle.putStringArray(key, value);
    }

    public String[] getStringArray(String key) {
        return bundle.getStringArray(key);
    }

    public void putIntArray(String key, int[] value) {
        bundle.putIntArray(key, value);
    }

    public int[] getIntArray(String key) {
        return bundle.getIntArray(key);
    }

    public void putLongArray(String key, long[] value) {
        bundle.putLongArray(key, value);
    }

    public long[] getLongArray(String key) {
        return bundle.getLongArray(key);
    }

    public void putDataMap(String key, DataMap value) {
        // Not implemented for stub
    }

    public DataMap getDataMap(String key) {
        return null;
    }

    public void putAll(DataMap dataMap) {
        bundle.putAll(dataMap.bundle);
    }

    public android.os.Bundle toBundle() {
        return bundle;
    }

    public static DataMap fromBundle(android.os.Bundle bundle) {
        DataMap dataMap = new DataMap();
        dataMap.bundle = bundle;
        return dataMap;
    }
}