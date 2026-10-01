package com.tech.ayugram.messenger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import androidx.annotation.Keep;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tech.ayugram.ApplicationLoader;

import java.util.concurrent.ConcurrentHashMap;

@Keep
public class ImageLoader {
    private static ImageLoader[] instances = new ImageLoader[3];
    
    private final ConcurrentHashMap<String, Object> loadingImages = new ConcurrentHashMap<>();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private ImageLoader(int accountNum) {
    }

    public static ImageLoader getInstance(int num) {
        if (instances[num] == null) {
            instances[num] = new ImageLoader(num);
        }
        return instances[num];
    }

    public static ImageLoader getInstance() {
        return getInstance(0);
    }

    public void loadImage(String url, ImageView imageView) {
        loadImage(url, imageView, null, 0, 0);
    }

    public void loadImage(String url, ImageView imageView, Drawable placeholder) {
        loadImage(url, imageView, placeholder, 0, 0);
    }

    public void loadImage(String url, ImageView imageView, int width, int height) {
        loadImage(url, imageView, null, width, height);
    }

    public void loadImage(String url, ImageView imageView, Drawable placeholder, int width, int height) {
        if (url == null || url.isEmpty()) {
            if (placeholder != null) {
                imageView.setImageDrawable(placeholder);
            }
            return;
        }

        Context context = ApplicationLoader.getApplicationContext();
        RequestOptions options = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(placeholder)
            .error(placeholder);

        if (width > 0 && height > 0) {
            options = options.override(width, height);
        }

        Glide.with(context)
            .load(url)
            .apply(options)
            .into(imageView);
    }

    public void loadImageThumb(String url, ImageView imageView, int size) {
        loadImage(url, imageView, null, size, size);
    }

    public void cancelLoad(ImageView imageView) {
        Glide.with(ApplicationLoader.getApplicationContext()).clear(imageView);
    }

    public boolean isLoadingImage(String url) {
        return loadingImages.containsKey(url);
    }

    public void clearMemoryCache() {
        Glide.get(ApplicationLoader.getApplicationContext()).clearMemory();
    }

    public void clearDiskCache() {
        mainHandler.post(() -> 
            Glide.get(ApplicationLoader.getApplicationContext()).clearDiskCache()
        );
    }
}