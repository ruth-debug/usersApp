package com.ruth.myapplication.model;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;


@Entity(tableName = "users", indices = @Index(value = {"id_name", "id_value"}))
public class User {
    @PrimaryKey(autoGenerate = true)
    Long user_id = null;

    @SerializedName("email")
    @ColumnInfo(name = "email")
    String email;

    @Embedded
    ID id;

    @Embedded
    Name name;

    @Embedded
    DOB dob;

    @Embedded
    Picture picture;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public DOB getDob() {
        return dob;
    }

    public void setDob(DOB dob) {
        this.dob = dob;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getDaysToBirthday() {
        return String.valueOf(getDiff());
    }


    public long getDiff(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDob().getBirthday());
        cal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        if (cal.getTime().getTime() < System.currentTimeMillis())
            cal.add(Calendar.YEAR,1);
        return TimeUnit.DAYS.convert(  cal.getTime().getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }
}

