package com.ruth.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ID {
    @NonNull
    @SerializedName("name")
    @ColumnInfo(name = "id_name")
    String name;

    @NonNull
    @SerializedName("value")
    @ColumnInfo(name = "id_value")
    String value;

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getValue() {
        return value;
    }

    public void setValue(@NonNull String value) {
        this.value = value;
    }
}
