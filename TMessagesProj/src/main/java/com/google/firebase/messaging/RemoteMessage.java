package com.google.firebase.messaging;

import android.os.Bundle;

public class RemoteMessage {
    private String from;
    private String to;
    private String messageId;
    private String messageType;
    private String collapseKey;
    private long sentTime;
    private long ttl;
    private Bundle data;
    private Notification notification;

    public RemoteMessage(Bundle data) {
        this.data = data;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getCollapseKey() {
        return collapseKey;
    }

    public long getSentTime() {
        return sentTime;
    }

    public long getTtl() {
        return ttl;
    }

    public Bundle getData() {
        return data;
    }

    public Notification getNotification() {
        return notification;
    }

    public static class Notification {
        private String title;
        private String body;
        private String icon;
        private String color;
        private String sound;
        private String tag;
        private String clickAction;
        private String channelId;
        private String imageUrl;
        private long when;

        public String getTitle() {
            return title;
        }

        public String getBody() {
            return body;
        }

        public String getIcon() {
            return icon;
        }

        public String getColor() {
            return color;
        }

        public String getSound() {
            return sound;
        }

        public String getTag() {
            return tag;
        }

        public String getClickAction() {
            return clickAction;
        }

        public String getChannelId() {
            return channelId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public long getWhen() {
            return when;
        }
    }
}