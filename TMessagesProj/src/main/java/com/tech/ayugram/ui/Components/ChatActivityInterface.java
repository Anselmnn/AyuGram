package com.tech.ayugram.ui.Components;

import com.tech.ayugram.messenger.ChatObject;
import com.tech.ayugram.tgnet.TLRPC;
import com.tech.ayugram.ui.ActionBar.ActionBar;
import com.tech.ayugram.ui.ActionBar.Theme;

public interface ChatActivityInterface {

    default ChatObject.Call getGroupCall() {
        return null;
    }

    default TLRPC.Chat getCurrentChat() {
        return null;
    }

    default TLRPC.User getCurrentUser() {
        return null;
    }

    long getDialogId();

    default void scrollToMessageId(int id, int i, boolean b, int i1, boolean b1, int i2) {

    }

    default boolean shouldShowImport() {
        return false;
    }

    default boolean openedWithLivestream() {
        return false;
    }

    default long getMergeDialogId() {
        return 0;
    }

    default long getTopicId() {
        return 0;
    }

    default boolean isRightFragment() {
        return false;
    }

    ChatAvatarContainer getAvatarContainer();

    default void checkAndUpdateAvatar() {

    }

    SizeNotifierFrameLayout getContentView();

    ActionBar getActionBar();

    Theme.ResourcesProvider getResourceProvider();
}
