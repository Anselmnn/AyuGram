package com.tech.ayugram.firebase.stub;

public class FirebaseInstanceId {
    public static FirebaseInstanceId getInstance() { return new FirebaseInstanceId(); }

    public String getToken() { return null; }
    public String getId() { return ""; }
    public void deleteInstanceId() {}
    public void deleteToken(String authorizedEntity, String scope) {}
}