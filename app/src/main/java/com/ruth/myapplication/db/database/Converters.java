package com.ruth.myapplication.db.database;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converters {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @TypeConverter
    public static Date fromTimestamp(String value) throws ParseException {
        return value == null ? null : format.parse(value);
    }

    @TypeConverter
    public static String dateToTimestamp(Date date) {
        return date == null ? null : format.format(date);
    }
}
