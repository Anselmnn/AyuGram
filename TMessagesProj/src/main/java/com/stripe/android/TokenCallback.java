package com.stripe.android;

import com.stripe.android.model.Token;

public interface TokenCallback {
    void onSuccess(Token token);
    void onError(Exception error);
}