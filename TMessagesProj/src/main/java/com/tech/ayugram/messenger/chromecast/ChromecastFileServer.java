package com.tech.ayugram.messenger.chromecast;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Phase 2: Stub implementation of ChromecastFileServer (Cast dependency removed)
 */
public class ChromecastFileServer {
    private static String host = "localhost";
    private static int port = 8080;

    public static void startServer(Context context) {
        // Stub: no server
    }

    public static void stopServer() {
        // Stub
    }

    public static String getHost() {
        return host;
    }

    public static int getPort() {
        return port;
    }

    public static String getUrlToSource(String host, String path) {
        return "http://" + host + ":" + port + "/" + path;
    }

    public static String saveCover(File cover) throws IOException {
        // Stub: return empty path
        return "";
    }

    public static void serveFile(String path, OutputStream outputStream) throws IOException {
        // Stub
    }

    public static String getMimeType(String path) {
        return "application/octet-stream";
    }
}