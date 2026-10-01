package com.tech.ayugram.messenger;

import androidx.annotation.Keep;

import com.tech.ayugram.tgnet.TLRPC;

@Keep
public class MessageObject {
    public TLRPC.Message message;
    public TLRPC.Message replyMessage;
    public TLRPC.Message forwardedMessage;
    public boolean isOut;
    public boolean isUnread;
    public boolean isMuted;
    public boolean isSecret;
    public int type = 0; // 0 = text, 1 = photo, 2 = video, 3 = document, 4 = audio, 5 = voice, 6 = round video, 7 = sticker, 8 = contact, 9 = location, 10 = poll, 11 = game, 12 = invoice
    
    public String caption = "";
    public String text = "";
    public String mediaPath = "";
    public int mediaWidth = 0;
    public int mediaHeight = 0;
    public int duration = 0;
    
    public MessageObject() {
    }
    
    public MessageObject(TLRPC.Message message) {
        this.message = message;
        this.text = message.message;
        this.isOut = message.out;
    }
    
    public boolean isVideo() {
        return type == 2;
    }
    
    public boolean isPhoto() {
        return type == 1;
    }
    
    public boolean isRoundVideo() {
        return type == 6;
    }
    
    public boolean isVoice() {
        return type == 5;
    }
    
    public boolean isMusic() {
        return type == 4;
    }
    
    public boolean isDocument() {
        return type == 3;
    }
    
    public boolean isSticker() {
        return type == 7;
    }
    
    public boolean isGif() {
        return type == 1 && message.gif;
    }
}