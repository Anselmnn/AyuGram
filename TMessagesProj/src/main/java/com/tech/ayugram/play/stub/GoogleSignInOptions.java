package com.tech.ayugram.play.stub;

import android.os.Parcel;
import android.os.Parcelable;

public class GoogleSignInOptions implements Parcelable {
    public static final int DEFAULT_SIGN_IN = 1;
    public static final int SIGN_IN = 2;

    private boolean requestIdToken = false;
    private String serverClientId;
    private boolean requestEmail = false;
    private boolean requestProfile = false;
    private boolean requestId = false;

    private GoogleSignInOptions() {}

    public static class Builder {
        private boolean requestIdToken = false;
        private String serverClientId;
        private boolean requestEmail = false;
        private boolean requestProfile = false;
        private boolean requestId = false;

        public Builder() {}

        public Builder requestIdToken(String serverClientId) {
            this.requestIdToken = true;
            this.serverClientId = serverClientId;
            return this;
        }

        public Builder requestServerAuthCode(String serverClientId) {
            this.serverClientId = serverClientId;
            return this;
        }

        public Builder requestEmail() {
            this.requestEmail = true;
            return this;
        }

        public Builder requestProfile() {
            this.requestProfile = true;
            return this;
        }

        public Builder requestId() {
            this.requestId = true;
            return this;
        }

        public GoogleSignInOptions build() {
            return new GoogleSignInOptions();
        }
    }

    public boolean isRequestIdToken() { return requestIdToken; }
    public String getServerClientId() { return serverClientId; }
    public boolean isRequestEmail() { return requestEmail; }
    public boolean isRequestProfile() { return requestProfile; }
    public boolean isRequestId() { return requestId; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (requestIdToken ? 1 : 0));
        dest.writeString(serverClientId);
        dest.writeByte((byte) (requestEmail ? 1 : 0));
        dest.writeByte((byte) (requestProfile ? 1 : 0));
        dest.writeByte((byte) (requestId ? 1 : 0));
    }

    public static final Creator<GoogleSignInOptions> CREATOR = new Creator<GoogleSignInOptions>() {
        @Override
        public GoogleSignInOptions createFromParcel(Parcel in) {
            GoogleSignInOptions options = new GoogleSignInOptions();
            options.requestIdToken = in.readByte() != 0;
            options.serverClientId = in.readString();
            options.requestEmail = in.readByte() != 0;
            options.requestProfile = in.readByte() != 0;
            options.requestId = in.readByte() != 0;
            return options;
        }

        @Override
        public GoogleSignInOptions[] newArray(int size) {
            return new GoogleSignInOptions[size];
        }
    };
}