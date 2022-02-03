package com.ruth.myapplication.network.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ruth.myapplication.App;
import com.ruth.myapplication.db.Dao.UserDao;
import com.ruth.myapplication.db.database.AppDatabase;
import com.ruth.myapplication.db.database.AppDbHelper;
import com.ruth.myapplication.db.database.DBHelper;
import com.ruth.myapplication.model.User;
import com.ruth.myapplication.model.UsersResponse;
import com.ruth.myapplication.network.retrofit.ApiService;
import com.ruth.myapplication.network.retrofit.NetworkClient;
import com.ruth.myapplication.network.retrofit.Resource;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

import static com.ruth.myapplication.utils.Constants.LOADED;
import static com.ruth.myapplication.utils.Constants.PREF_NAME;

public class Repository {

    private volatile static Repository repository = null;
    private final SharedPreferences sharedPref;
    private ApiService networkApi;
    private AppDbHelper dbHelper;


    private Repository(Application application) {
        networkApi = NetworkClient.getInstance();
        dbHelper = AppDbHelper.getInstance(application);
        sharedPref = application.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);

    }

    public static Repository getInstance(Application application) {
        if (repository == null) {
            synchronized (Repository.class) {
                repository = new Repository(application);
            }
        }
        return repository;
    }

    /***
     *
     * @param limit
     * @return List of users at first time from server else from local db
     */
    public LiveData<Resource<List<User>>> fetchUsers(int limit) {
        MutableLiveData<Resource<List<User>>> newsData = new MutableLiveData();

        if (sharedPref.getBoolean(LOADED, false))
            return fetchFromLocal(newsData);
        return fetchFromServer(limit, newsData);
    }

    /**
     *
     * @param limit
     * @return List of users from server
     */
    public MutableLiveData<Resource<List<User>>> fetchFromServer(int limit) {
        MutableLiveData<Resource<List<User>>> newsData = new MutableLiveData();
        return fetchFromServer(limit, newsData);
    }

    public MutableLiveData<Resource<List<User>>> fetchFromServer(int limit, MutableLiveData<Resource<List<User>>> newsData) {

        networkApi.fetchUsers(limit).enqueue(new retrofit2.Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.isSuccessful()) {
                    sharedPref.edit().putBoolean(LOADED, true).apply();
                    saveUsers(response.body().getResults(), newsData);
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
               newsData.setValue(Resource.error(t.getMessage() ,null));
            }
        });
        return newsData;
    }

    /**
     *
     * @param newsData
     * @return get users from local db
     */
    @SuppressLint("CheckResult")
    private MutableLiveData<Resource<List<User>>> fetchFromLocal(MutableLiveData<Resource<List<User>>> newsData) {
        dbHelper.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null) {
                        newsData.setValue(Resource.success(response));
                    }
                }, throwable -> {
                    newsData.setValue(Resource.error(throwable.getMessage(), null));

                });
        return newsData;
    }

    /**
     *
     * @param users - new users
     * @param newsData
     * @return new users
     * First delete old date
     * Second save new users
     * Third retrieve the new users with ids from db
     */
    private MutableLiveData<Resource<List<User>>> saveUsers(List<User> users, MutableLiveData<Resource<List<User>>> newsData) {

        dbHelper.refreshUsers(users)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    newsData.setValue(Resource.success(response));
                }, throwable -> {
                    newsData.setValue(Resource.error(throwable.getMessage(), null));
                });
        return newsData;

    }

    /**
     *
     * @param value - user_id
     * @return User according its id
     */

    public MutableLiveData<Resource<User>> getUser(Long value) {
        MutableLiveData<Resource<User>> newsData = new MutableLiveData<>();
        dbHelper.getUser(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null) {
                        newsData.setValue(Resource.success(response));
                    }
                }, throwable -> {
                    newsData.setValue(Resource.error(throwable.getMessage(), null));

                });
        return newsData;
    }

}
