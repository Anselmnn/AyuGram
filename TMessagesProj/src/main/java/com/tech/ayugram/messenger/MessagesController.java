package com.tech.ayugram.messenger;

import android.content.Context;

import androidx.annotation.Keep;
import androidx.lifecycle.LiveData;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.tgnet.ConnectionsManager;
import com.tech.ayugram.tgnet.RequestDelegate;
import com.tech.ayugram.tgnet.TLObject;
import com.tech.ayugram.tgnet.TLRPC;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@Keep
public class MessagesController {
    private static MessagesController[] instances = new MessagesController[3];
    
    private final ConcurrentHashMap<Long, TLRPC.Dialog> dialogs = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, TLRPC.User> users = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, TLRPC.Chat> chats = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, ArrayList<TLRPC.Message>> messages = new ConcurrentHashMap<>();
    
    private boolean loadingDialogs = false;
    private boolean loadingMessages = false;

    private MessagesController(int accountNum) {
    }

    public static MessagesController getInstance(int num) {
        if (instances[num] == null) {
            instances[num] = new MessagesController(num);
        }
        return instances[num];
    }

    public static MessagesController getInstance() {
        return getInstance(0);
    }

    public void getDialogs(final int offset, final int limit, final boolean force, final RequestDelegate delegate) {
        if (loadingDialogs && !force) return;
        loadingDialogs = true;
        
        // Simulate loading from local database
        ConnectionsManager.getInstance(UserConfig.getInstance().currentAccount).sendRequest(
            new TLRPC.TL_messages_getDialogs(),
            (response, error) -> {
                loadingDialogs = false;
                if (error == null) {
                    TLRPC.messages_Dialogs dialogsRes = (TLRPC.messages_Dialogs) response;
                    processDialogs(dialogsRes);
                }
                if (delegate != null) {
                    delegate.run(response, error);
                }
            }
        );
    }

    private void processDialogs(TLRPC.messages_Dialogs dialogsRes) {
        for (TLRPC.User user : dialogsRes.users) {
            users.put(user.id, user);
        }
        for (TLRPC.Chat chat : dialogsRes.chats) {
            chats.put(chat.id, chat);
        }
        for (TLRPC.Dialog dialog : dialogsRes.dialogs) {
            dialogs.put(dialog.id, dialog);
        }
    }

    public void getMessages(long dialogId, int offsetId, int limit, RequestDelegate delegate) {
        if (loadingMessages) return;
        loadingMessages = true;
        
        TLRPC.TL_messages_getHistory req = new TLRPC.TL_messages_getHistory();
        req.peer = getInputPeer(dialogId);
        req.offset_id = offsetId;
        req.limit = limit;
        
        ConnectionsManager.getInstance(UserConfig.getInstance().currentAccount).sendRequest(req, (response, error) -> {
            loadingMessages = false;
            if (delegate != null) {
                delegate.run(response, error);
            }
        });
    }

    public void sendMessage(TLRPC.Message message) {
        // Send message logic
    }

    public TLRPC.User getUser(long uid) {
        return users.get(uid);
    }

    public TLRPC.Chat getChat(long cid) {
        return chats.get(cid);
    }

    public TLRPC.Dialog getDialog(long did) {
        return dialogs.get(did);
    }

    private TLRPC.InputPeer getInputPeer(long dialogId) {
        if (dialogId > 0) {
            TLRPC.TL_inputPeerUser peer = new TLRPC.TL_inputPeerUser();
            peer.user_id = dialogId;
            peer.access_hash = getUser(dialogId) != null ? getUser(dialogId).access_hash : 0;
            return peer;
        } else {
            TLRPC.TL_inputPeerChat peer = new TLRPC.TL_inputPeerChat();
            peer.chat_id = -dialogId;
            return peer;
        }
    }

    public void performLogout() {
        dialogs.clear();
        users.clear();
        chats.clear();
        messages.clear();
    }
}