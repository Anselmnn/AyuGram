package com.tech.ayugram.play.stub;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class Status implements Parcelable {
    private int statusCode;
    private String statusMessage;

    public Status() {
        this.statusCode = 0;
    }

    public Status(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public boolean isSuccess() {
        return statusCode == 0;
    }

    public boolean isCanceled() {
        return statusCode == 16;
    }

    public boolean isInterrupted() {
        return statusCode == 14;
    }

    public static Status getStatusFromIntent(Intent intent) {
        return new Status();
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(statusCode);
        dest.writeString(statusMessage);
    }

    public static final Creator<Status> CREATOR = new Creator<Status>() {
        @Override
        public Status createFromParcel(Parcel in) {
            Status status = new Status();
            status.statusCode = in.readInt();
            status.statusMessage = in.readString();
            return status;
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };
}