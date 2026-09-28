package com.google.firebase.auth;

import android.app.Activity;

public class AssistActionBuilder {
    public AssistActionBuilder() {}

    public AssistActionBuilder setActionToken(String token) {
        return this;
    }

    public AssistActionBuilder setActionStatus(int status) {
        return this;
    }

    public Action build() {
        return new Action();
    }
}