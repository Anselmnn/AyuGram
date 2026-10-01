package com.google.android.gms.wearable;

public class Wearable {
    public static final String ACTION_DATA_CHANGED = "com.google.android.gms.wearable.DATA_CHANGED";
    public static final String ACTION_MESSAGE_RECEIVED = "com.google.android.gms.wearable.MESSAGE_RECEIVED";

    private Wearable() {
    }

    public static MessageClient getMessageClient(android.content.Context context) {
        return new MessageClient(context);
    }

    public static MessageClient getMessageClient(android.app.Activity activity) {
        return new MessageClient(activity);
    }

    public static DataClient getDataClient(android.content.Context context) {
        return new DataClient(context);
    }

    public static DataClient getDataClient(android.app.Activity activity) {
        return new DataClient(activity);
    }
}