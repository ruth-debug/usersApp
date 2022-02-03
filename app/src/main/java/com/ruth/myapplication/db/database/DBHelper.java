package com.ruth.myapplication.db.database;

import com.ruth.myapplication.model.User;
import java.util.List;

import io.reactivex.Observable;


public interface DBHelper {

    Observable<List<User>> refreshUsers(List<User> users);

    Observable<List<User>> getUsers();

    Observable<User> getUser(Long id);

    Observable<Boolean> deleteAll();

}
