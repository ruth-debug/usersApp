package com.ruth.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class Name {
    @NonNull
    @SerializedName("first")
    @ColumnInfo(name = "first_name")
    String firstName;

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    @SerializedName("last")
    @ColumnInfo(name = "last_name")
    String lastName;

}
