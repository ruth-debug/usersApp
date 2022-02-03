package com.ruth.myapplication.db.database;


import android.content.Context;

import androidx.databinding.Observable;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ruth.myapplication.db.Dao.UserDao;
import com.ruth.myapplication.model.User;

import static com.ruth.myapplication.utils.Constants.DATABASE_NAME;

@Database(entities = {User.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

}