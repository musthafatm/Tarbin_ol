package com.iroid.tarbinol.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Acer on 30-Aug-17.
 */

public interface WebService {

    @FormUrlEncoded
    @POST("Salesexcutive/login")
    Call<JsonObject> login(@Field("username") String userName, @Field("password")  String password);

    @GET("Salesexcutive/login")
    Call<JsonObject> loginResponse(@Query("username") String userName, @Query("password")  String password);

}
