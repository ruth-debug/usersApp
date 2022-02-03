package com.ruth.myapplication.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ruth.myapplication.model.User;
import com.ruth.myapplication.network.repository.Repository;
import com.ruth.myapplication.network.retrofit.Resource;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private Repository repository;
    public LiveData<Resource<List<User>>> getAllUsers;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public void  fetchUsers() {
        getAllUsers = repository.fetchUsers(10);
    }

    public void refreshUsers() {
        getAllUsers = repository.fetchFromServer(10);
    }

    public LiveData<Resource<List<User>>> getAllUsers() {
        return getAllUsers;
    }
}