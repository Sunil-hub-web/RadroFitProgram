package com.example.radrofitprogram;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetroInterface {

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<RegResponse> createUser(@Body JsonObject jsonObject);

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<LoginResponse> LoginUser(@Body JsonObject jsonObject);
}