package com.ruth.myapplication.network.retrofit;

import com.ruth.myapplication.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkClient {
    public final static String BASE_URL = BuildConfig.SERVER_URL;
    private volatile static ApiService retrofit = null;
    private NetworkClient() {
    }

    public static ApiService getInstance() {
        if (retrofit == null) {
            synchronized (Retrofit.class) {
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(1, TimeUnit.SECONDS)
                        .connectTimeout(1, TimeUnit.SECONDS)
                        .writeTimeout(1, TimeUnit.SECONDS)
                        .build();
                retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .build().create(ApiService.class);
            }
        }
        return retrofit;
    }


}
