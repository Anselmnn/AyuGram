package com.tech.ayugram.play.stub;

import android.content.Context;

public class IntegrityManagerFactory {
    public IntegrityManager create(Context context) {
        return new IntegrityManager();
    }
}