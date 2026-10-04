package org.telegram.messenger;

import androidx.annotation.Nullable;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

public class MessageSuggestionParams {
    public final long time;

    private MessageSuggestionParams(long time) {
        this.time = time;
    }

    public @Nullable TLRPC.SuggestedPost toTl() {
        TLRPC.SuggestedPost suggestedPost = new TLRPC.SuggestedPost();

        if (time > 0) {
            suggestedPost.schedule_date = (int) time;
            suggestedPost.flags |= TLObject.FLAG_0;
        }

        return suggestedPost;
    }

    public boolean isEmpty() {
        return time <= 0;
    }

    public static MessageSuggestionParams empty() {
        return new MessageSuggestionParams(0);
    }

    public static MessageSuggestionParams of(long time) {
        return new MessageSuggestionParams(time);
    }

    public static MessageSuggestionParams of(TLRPC.SuggestedPost suggestedPost) {
        if (suggestedPost == null) {
            return empty();
        }
        return new MessageSuggestionParams(suggestedPost.schedule_date);
    }

    public static MessageSuggestionParams of(TLRPC.TL_messageActionSuggestedPostApproval approval) {
        return new MessageSuggestionParams(approval.schedule_date);
    }
}
