package com.tech.ayugram.play.stub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class GoogleSignInClient implements Parcelable {
    public com.tech.ayugram.play.stub.Task<GoogleSignInAccount> silentSignIn() {
        return com.tech.ayugram.play.stub.Task.forResult(null);
    }

    public com.tech.ayugram.play.stub.Task<GoogleSignInAccount> signIn() {
        return com.tech.ayugram.play.stub.Task.forResult(null);
    }

    public Intent getSignInIntent() {
        return new Intent();
    }

    public com.tech.ayugram.play.stub.Task<Void> signOut() {
        return com.tech.ayugram.play.stub.Task.forResult(null);
    }

    public com.tech.ayugram.play.stub.Task<Void> revokeAccess() {
        return com.tech.ayugram.play.stub.Task.forResult(null);
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {}

    public static final Creator<GoogleSignInClient> CREATOR = new Creator<GoogleSignInClient>() {
        @Override
        public GoogleSignInClient createFromParcel(Parcel in) { return new GoogleSignInClient(); }
        @Override
        public GoogleSignInClient[] newArray(int size) { return new GoogleSignInClient[size]; }
    };
}