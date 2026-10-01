package com.tech.ayugram.tgnet;

import android.os.Build;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.UserConfig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Keep
public class ConnectionsManager {
    private static ConnectionsManager[] instances = new ConnectionsManager[3];
    
    private final AtomicInteger requestId = new AtomicInteger(1);
    private final ConcurrentHashMap<Integer, RequestDelegate> pendingRequests = new ConcurrentHashMap<>();
    
    private String deviceModel = ApplicationLoader.DEVICE_MODEL;
    private String systemVersion = ApplicationLoader.DEVICE_SDK;
    private String appVersion = ApplicationLoader.APP_VERSION_NAME;
    private String systemLangCode = "en";
    private String langCode = "en";
    private String langPack = "";
    private int timeZoneOffset = 0;
    
    // Connection state
    private int connectionState = ConnectionStateWaitingForNetwork;
    private boolean isConnected = false;
    
    public static final int ConnectionStateWaitingForNetwork = 0;
    public static final int ConnectionStateConnecting = 1;
    public static final int ConnectionStateUpdating = 2;
    public static final int ConnectionStateConnected = 3;

    private ConnectionsManager(int accountNum) {
    }

    public static ConnectionsManager getInstance(int num) {
        if (instances[num] == null) {
            instances[num] = new ConnectionsManager(num);
        }
        return instances[num];
    }

    public static ConnectionsManager getInstance() {
        return getInstance(0);
    }

    public void sendRequest(TLObject request, RequestDelegate delegate) {
        sendRequest(request, delegate, 0, 0, false, false);
    }

    public void sendRequest(TLObject request, RequestDelegate delegate, int timeout, int flags, boolean quickAck, boolean priority) {
        int reqId = requestId.getAndIncrement();
        pendingRequests.put(reqId, delegate);
        
        // Simulate network request
        new Thread(() -> {
            try {
                Thread.sleep(100); // Simulate network delay
                
                // Generate mock response based on request type
                TLObject response = generateMockResponse(request);
                TLRPC.TL_error error = null;
                
                RequestDelegate callback = pendingRequests.remove(reqId);
                if (callback != null) {
                    callback.run(response, error);
                }
            } catch (Exception e) {
                RequestDelegate callback = pendingRequests.remove(reqId);
                if (callback != null) {
                    callback.run(null, new TLRPC.TL_error());
                }
            }
        }).start();
    }

    private TLObject generateMockResponse(TLObject request) {
        // §6.1 Stories blocking - return empty responses
        if (request instanceof TLRPC.TL_stories_getAllStories) {
            TLRPC.TL_stories_allStories empty = new TLRPC.TL_stories_allStories();
            empty.stories = new java.util.ArrayList<>();
            return empty;
        } else if (request instanceof TLRPC.TL_stories_getPeerStories) {
            TLRPC.TL_stories_peerStories empty = new TLRPC.TL_stories_peerStories();
            empty.stories = new java.util.ArrayList<>();
            return empty;
        } else if (request instanceof TLRPC.TL_stories_getStoriesArchive) {
            TLRPC.TL_stories_storiesArchive empty = new TLRPC.TL_stories_storiesArchive();
            empty.stories = new java.util.ArrayList<>();
            return empty;
        }
        
        if (request instanceof TLRPC.TL_messages_getDialogs) {
            TLRPC.messages_Dialogs dialogs = new TLRPC.messages_Dialogs();
            dialogs.dialogs = new java.util.ArrayList<>();
            dialogs.users = new java.util.ArrayList<>();
            dialogs.chats = new java.util.ArrayList<>();
            return dialogs;
        } else if (request instanceof TLRPC.TL_messages_getHistory) {
            TLRPC.messages_Messages messages = new TLRPC.messages_Messages();
            messages.messages = new java.util.ArrayList<>();
            messages.users = new java.util.ArrayList<>();
            messages.chats = new java.util.ArrayList<>();
            return messages;
        } else if (request instanceof TLRPC.TL_messages_sendMessage) {
            TLRPC.TL_messages_sentMessage sent = new TLRPC.TL_messages_sentMessage();
            sent.id = (int) (System.currentTimeMillis() & 0xFFFFFFFFL);
            sent.date = (int) (System.currentTimeMillis() / 1000);
            return sent;
        } else if (request instanceof TLRPC.TL_messages_getReplies) {
            TLRPC.messages_Messages messages = new TLRPC.messages_Messages();
            messages.messages = new java.util.ArrayList<>();
            messages.users = new java.util.ArrayList<>();
            messages.chats = new java.util.ArrayList<>();
            messages.count = 0;
            return messages;
        }
        return new TLObject() {
            @Override
            public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
        };
    }

    public void setDeviceInfo(String model, String sdk, String appVer, String fingerprint) {
        this.deviceModel = model;
        this.systemVersion = sdk;
        this.appVersion = appVer;
        // fingerprint used for device identification
    }

    public void setSystemLangCode(String langCode) {
        this.systemLangCode = langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public void setLangPack(String langPack) {
        this.langPack = langPack;
    }

    public void setTimeZoneOffset(int offset) {
        this.timeZoneOffset = offset;
    }

    public int getConnectionState() {
        return connectionState;
    }

    public void setConnectionState(int state) {
        this.connectionState = state;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        this.isConnected = connected;
    }

    public void applyDatacenterAddress(String address, int port) {
        // Apply datacenter address
    }

    public void cleanup() {
        pendingRequests.clear();
    }
    
    // For offline-first (§1.3)
    public boolean isOffline() {
        return connectionState == ConnectionStateWaitingForNetwork ||
               connectionState == ConnectionStateConnecting ||
               connectionState == ConnectionStateUpdating;
    }
}