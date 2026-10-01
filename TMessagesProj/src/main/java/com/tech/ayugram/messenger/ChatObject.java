package com.tech.ayugram.messenger;

import androidx.annotation.Keep;

import com.tech.ayugram.tgnet.TLRPC;

@Keep
public class ChatObject {
    public static boolean isChannel(TLRPC.Chat chat) {
        return chat != null && chat.broadcast;
    }

    public static boolean isMegagroup(TLRPC.Chat chat) {
        return chat != null && chat.megagroup;
    }

    public static boolean isForum(TLRPC.Chat chat) {
        return chat != null && chat.is_forum;
    }

    public static boolean isGroup(TLRPC.Chat chat) {
        return chat != null && !chat.broadcast && !chat.megagroup;
    }

    public static boolean isChat(TLRPC.Chat chat) {
        return chat != null && chat.id < 0;
    }

    public static boolean isUser(long uid) {
        return uid > 0;
    }

    public static boolean isSecretChat(TLRPC.Chat chat) {
        return false; // Secret chats handled differently
    }

    public static String getChatName(TLRPC.Chat chat) {
        if (chat == null) return "";
        return chat.title != null ? chat.title : "";
    }
}