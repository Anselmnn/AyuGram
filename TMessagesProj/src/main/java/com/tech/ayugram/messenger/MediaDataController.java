package com.tech.ayugram.messenger;

import androidx.annotation.Keep;

import com.tech.ayugram.tgnet.TLRPC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Keep
public class MediaDataController {
    private static MediaDataController[] instances = new MediaDataController[3];
    
    private final ConcurrentHashMap<String, TLRPC.Document> documents = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, TLRPC.Photo> photos = new ConcurrentHashMap<>();

    private MediaDataController(int accountNum) {
    }

    public static MediaDataController getInstance(int num) {
        if (instances[num] == null) {
            instances[num] = new MediaDataController(num);
        }
        return instances[num];
    }

    public static MediaDataController getInstance() {
        return getInstance(0);
    }

    public TLRPC.Document getDocument(String key) {
        return documents.get(key);
    }

    public void putDocument(String key, TLRPC.Document document) {
        documents.put(key, document);
    }

    public TLRPC.Photo getPhoto(String key) {
        return photos.get(key);
    }

    public void putPhoto(String key, TLRPC.Photo photo) {
        photos.put(key, photo);
    }

    public void cleanup() {
        documents.clear();
        photos.clear();
    }
}