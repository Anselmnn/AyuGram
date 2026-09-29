package com.tech.ayugram.play.stub;

import android.net.Uri;

public class GoogleSignInAccount {
    private String id;
    private String email;
    private String displayName;
    private String givenName;
    private String familyName;
    private android.net.Uri photoUrl;
    private String idToken;
    private String serverAuthCode;

    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getDisplayName() { return displayName; }
    public String getGivenName() { return givenName; }
    public String getFamilyName() { return familyName; }
    public android.net.Uri getPhotoUrl() { return photoUrl; }
    public String getIdToken() { return idToken; }
    public String getServerAuthCode() { return serverAuthCode; }
}