package com.google.firebase.auth;

import android.os.Bundle;

public class Action {
    public static class Builder {
        public static final int STATUS_TYPE_STARTED = 1;
        public static final int STATUS_TYPE_COMPLETED = 2;
        public static final int STATUS_TYPE_FAILED = 3;

        private String actionToken;
        private int statusType;
        private Bundle bundle;

        public Builder setActionToken(String token) {
            this.actionToken = token;
            return this;
        }

        public Builder setActionStatus(int status) {
            this.statusType = status;
            return this;
        }

        public Builder setAppIndexing(Bundle bundle) {
            this.bundle = bundle;
            return this;
        }

        public Action build() {
            return new Action();
        }
    }
}