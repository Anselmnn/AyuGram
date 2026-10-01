package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable;

public class GoogleSignInAccount implements Parcelable {
    private String id;
    private String email;
    private String displayName;
    private String givenName;
    private String familyName;
    private String photoUrl;
    private String serverAuthCode;
    private String idToken;
    private String[] grantedScopes;

    public GoogleSignInAccount() {
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getServerAuthCode() {
        return serverAuthCode;
    }

    public String getIdToken() {
        return idToken;
    }

    public String[] getGrantedScopes() {
        return grantedScopes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new Parcelable.Creator<GoogleSignInAccount>() {
        @Override
        public GoogleSignInAccount createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public GoogleSignInAccount[] newArray(int size) {
            return new GoogleSignInAccount[size];
        }
    };
}