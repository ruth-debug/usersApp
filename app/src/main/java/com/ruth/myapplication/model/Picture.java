package com.ruth.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Picture {
    @NonNull
    @SerializedName("large")
    @ColumnInfo(name = "large")
    String large;

    @NonNull
    @SerializedName("medium")
    @ColumnInfo(name = "medium")
    String medium;

    @NonNull
    @SerializedName("thumbnail")
    @ColumnInfo(name = "thumbnail")
    String thumbnail;

    @NonNull
    public String getLarge() {
        return large;
    }

    public void setLarge(@NonNull String large) {
        this.large = large;
    }

    @NonNull
    public String getMedium() {
        return medium;
    }

    public void setMedium(@NonNull String medium) {
        this.medium = medium;
    }

    @NonNull
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(@NonNull String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
