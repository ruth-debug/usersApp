package com.ruth.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DOB {
    @NonNull
    @SerializedName("date")
    @ColumnInfo(name = "birthday")
    Date birthday;

    @NonNull
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(@NonNull Date birthday) {
        this.birthday = birthday;
    }
}
