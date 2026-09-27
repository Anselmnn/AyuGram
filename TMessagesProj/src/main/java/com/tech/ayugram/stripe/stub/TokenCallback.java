package com.tech.ayugram.stripe.stub;

public interface TokenCallback {
    void onSuccess(Token token);
    void onError(Exception error);
}