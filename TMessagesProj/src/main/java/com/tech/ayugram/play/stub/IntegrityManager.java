package com.tech.ayugram.play.stub;

import android.content.Context;

public class IntegrityManager {
    public IntegrityManager() {}

    public static IntegrityManager create(Context context) {
        return new IntegrityManager();
    }

    public com.tech.ayugram.play.stub.Task<IntegrityTokenResponse> requestIntegrityToken(IntegrityTokenRequest request) {
        return com.tech.ayugram.play.stub.Task.forResult(new IntegrityTokenResponse());
    }
}