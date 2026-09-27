package com.tech.ayugram.ui.Stories;

import android.text.TextUtils;

import com.tech.ayugram.messenger.ChatObject;
import com.tech.ayugram.messenger.MessagesController;
import com.tech.ayugram.tgnet.TLRPC;

public class ChannelBoostUtilities {
    public static String createLink(int currentAccount, long dialogId) {
        TLRPC.Chat chat = MessagesController.getInstance(currentAccount).getChat(-dialogId);
        String username = ChatObject.getPublicUsername(chat);
        if (!TextUtils.isEmpty(username)) {
            return "https://t.me/boost/" + ChatObject.getPublicUsername(chat);
        } else {
            return "https://t.me/boost/?c=" + -dialogId;
        }
    }
}
