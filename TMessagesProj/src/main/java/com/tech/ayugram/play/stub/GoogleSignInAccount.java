package com.tech.ayugram.play.stub;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;

public class GoogleSignInAccount implements Parcelable {
    private String id;
    private String idToken;
    private String email;
    private String displayName;
    private String givenName;
    private String familyName;
    private android.net.Uri photoUrl;
    private String serverAuthCode;

    public GoogleSignInAccount() {}

    public String getId() { return id; }
    public String getIdToken() { return idToken; }
    public String getEmail() { return email; }
    public String getDisplayName() { return displayName; }
    public String getGivenName() { return givenName; }
    public String getFamilyName() { return familyName; }
    public android.net.Uri getPhotoUrl() { return photoUrl; }
    public String getServerAuthCode() { return serverAuthCode; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(idToken);
        dest.writeString(email);
        dest.writeString(displayName);
        dest.writeString(givenName);
        dest.writeString(familyName);
        dest.writeParcelable(photoUrl, flags);
        dest.writeString(serverAuthCode);
    }

    public static final Creator<GoogleSignInAccount> CREATOR = new Creator<GoogleSignInAccount>() {
        @Override
        public GoogleSignInAccount createFromParcel(Parcel in) {
            return new GoogleSignInAccount(in);
        }

        @Override
        public GoogleSignInAccount[] newArray(int size) {
            return new GoogleSignInAccount[size];
        }
    };

    private GoogleSignInAccount(Parcel in) {
        id = in.readString();
        idToken = in.readString();
        email = in.readString();
        displayName = in.readString();
        givenName = in.readString();
        familyName = in.readString();
        photoUrl = in.readParcelable(android.net.Uri.class.getClassLoader());
        serverAuthCode = in.readString();
    }
}