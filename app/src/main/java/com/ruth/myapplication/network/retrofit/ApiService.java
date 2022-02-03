package com.ruth.myapplication.network.retrofit;

import com.ruth.myapplication.model.UsersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("?")
    Call<UsersResponse> fetchUsers(@Query("results") int results);
}
