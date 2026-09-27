package com.tech.ayugram.firebase.stub;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class RemoteMessage implements Parcelable {
    private String from;
    private String to;
    private String messageId;
    private String messageType;
    private long sentTime;
    private long ttl;
    private Bundle data;
    private String collapseKey;
    private String messageId;
    private String senderId;
    private String category;
    private String originalPriority;
    private String priority;
    private Bundle notification;

    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getMessageId() { return messageId; }
    public String getMessageType() { return messageType; }
    public long getSentTime() { return sentTime; }
    public long getTtl() { return ttl; }
    public Bundle getData() { return data; }
    public String getCollapseKey() { return collapseKey; }
    public String getMessageId() { return messageId; }
    public String getSenderId() { return senderId; }
    public String getCategory() { return category; }
    public String getOriginalPriority() { return originalPriority; }
    public String getPriority() { return priority; }
    public Bundle getNotification() { return notification; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(from);
        dest.writeString(to);
        dest.writeString(messageId);
        dest.writeString(messageType);
        dest.writeLong(sentTime);
        dest.writeLong(ttl);
        dest.writeBundle(data);
        dest.writeString(collapseKey);
        dest.writeString(messageId);
        dest.writeString(senderId);
        dest.writeString(category);
        dest.writeString(originalPriority);
        dest.writeString(priority);
        dest.writeBundle(notification);
    }

    public static final Creator<RemoteMessage> CREATOR = new Creator<RemoteMessage>() {
        @Override
        public RemoteMessage createFromParcel(Parcel in) { return new RemoteMessage(in); }
        @Override
        public RemoteMessage[] newArray(int size) { return new RemoteMessage[size]; }
    };

    private RemoteMessage(Parcel in) {
        from = in.readString();
        to = in.readString();
        messageId = in.readString();
        messageType = in.readString();
        sentTime = in.readLong();
        ttl = in.readLong();
        data = in.readBundle();
        collapseKey = in.readString();
        messageId = in.readString();
        senderId = in.readString();
        category = in.readString();
        originalPriority = in.readString();
        priority = in.readString();
        notification = in.readBundle();
    }
}