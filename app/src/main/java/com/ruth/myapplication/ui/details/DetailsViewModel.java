package com.ruth.myapplication.ui.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ruth.myapplication.model.User;
import com.ruth.myapplication.network.repository.Repository;
import com.ruth.myapplication.network.retrofit.Resource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class DetailsViewModel extends AndroidViewModel {
    private Repository repository;
    public LiveData<Resource<User>> getUser;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public void setCurrent(Long id) {
        getUser = repository.getUser(id);
    }

    public LiveData<Resource<User>> getUser() {
        return getUser;
    }


    public void  startCount(){
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    public void accept(Long x) throws Exception {
                        // update your view here

                    }
                })
                .takeUntil(aLong -> aLong == 100)
                .doOnComplete(() -> {}
                        // do whatever you need on complete
                ).subscribe();
    }
}