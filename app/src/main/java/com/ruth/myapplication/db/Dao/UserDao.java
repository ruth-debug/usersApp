package com.ruth.myapplication.db.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.ruth.myapplication.model.User;

import java.util.List;

import io.reactivex.Single;

@Dao
public abstract class UserDao {
    @Transaction
    public List<User> refreshUsers(List<User> users){
        deleteAll();
        insert(users);
        return   getUsers().blockingGet();

//        return getUsers();
    };
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(List<User> users);

    @Query("SELECT DISTINCT * FROM users")
    public abstract Single<List<User>> getUsers();

    @Query("SELECT * FROM users where user_id = :id")
    public abstract Single<User> getUser(Long id);

    @Query("DELETE FROM users")
    public abstract void deleteAll();
}
