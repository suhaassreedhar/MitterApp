package com.suhaas.mitterapp;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by suhaas on 1/3/18.
 */

public interface ApiService {

    @POST("mittersample")
    Call<MitterResponse> submitPost(@Query("value") String value);
}
