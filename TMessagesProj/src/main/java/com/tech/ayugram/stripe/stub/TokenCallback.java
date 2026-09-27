package com.tech.ayugram.stripe.stub;

import com.tech.ayugram.stripe.stub.model.Token;

public interface TokenCallback {
    void onSuccess(Token token);
    void onError(Exception error);
}