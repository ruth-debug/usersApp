
package com.ruth.myapplication.db.database;

import android.content.Context;

import androidx.room.Room;
import com.ruth.myapplication.App;
import com.ruth.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import static com.ruth.myapplication.utils.Constants.DATABASE_NAME;

public class AppDbHelper implements DBHelper {
    private static AppDbHelper instance;


    public static AppDbHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDbHelper.class) {
                if (instance == null)
                    instance = new AppDbHelper(context);
            }
        }
        // Return the instance
        return instance;
    }

    private AppDbHelper(Context context) {
        mAppDatabase =  Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    private final AppDatabase mAppDatabase;


   /* @Override
    public Observable<List<User>> insertUsers(List<User> users) {
       return Completable.fromObservable(deleteAll()).andThen(Completable.fromCallable(()->{
           mAppDatabase.userDao().insert(users);
           return true;
       })).andThen(Completable.fromObservable(getUsers())).toObservable();

    }*/

    @Override
    public Observable<List<User>> refreshUsers(List<User> users) {
        return Observable.fromCallable(()->{
            List<User> us =  mAppDatabase.userDao().refreshUsers(users);
            return us;
        });
    }

    @Override
    public Observable<List<User>> getUsers() {
        return mAppDatabase.userDao().getUsers()
                .toObservable();
    }

    @Override
    public Observable<User> getUser(Long id) {
        return mAppDatabase.userDao().getUser(id)
                .toObservable();
    }


    @Override
    public Observable<Boolean> deleteAll() {
        return Observable.fromCallable(() -> {
            mAppDatabase.userDao().deleteAll();
            return true;
        });
    }
}
