package com.tech.ayugram.messenger;

import androidx.annotation.Keep;

import java.util.concurrent.ConcurrentHashMap;

@Keep
public class FileRefController {
    private final ConcurrentHashMap<String, Integer> refCounts = new ConcurrentHashMap<>();

    public void addRef(String file) {
        refCounts.merge(file, 1, Integer::sum);
    }

    public void removeRef(String file) {
        refCounts.computeIfPresent(file, (k, v) -> v <= 1 ? null : v - 1);
    }

    public int getRefCount(String file) {
        return refCounts.getOrDefault(file, 0);
    }
}