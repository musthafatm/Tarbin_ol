package com.iroid.tarbinol.api;

import com.google.gson.JsonObject;
import com.iroid.tarbinol.model.CheckinResponseModel;
import com.iroid.tarbinol.model.ProductDisplayResponseModel;
import com.iroid.tarbinol.model.ShopVisitResponseModel;

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

    @FormUrlEncoded
    @POST("Salesexcutive/chooseday")
    Call<JsonObject> chooseDay(@Field("employee_id") String empId);

    @FormUrlEncoded
    @POST("Salesexcutive/TodayTask")
    Call<ShopVisitResponseModel> todayTask(@Field("employee_id") String empId, @Field("day") String day);

    @FormUrlEncoded
    @POST("Salesexcutive/salesexecutiveCheckin")
    Call<CheckinResponseModel> check_in_Task(@Field("employee_id") String empId, @Field("day") String day, @Field("shop_id") String shopId);

    @FormUrlEncoded
    @POST("Salesexcutive/productDisplay")
    Call<ProductDisplayResponseModel> productDisplay(@Field("shop_id") String shopId);

    @FormUrlEncoded
    @POST("Salesexcutive/editProductDetails")
    Call<JsonObject> editProduct(@Field("shop_id") String shopId,@Field("employee_id") String empId, @Field("product_id") String productId, @Field("required") String required, @Field("in_stock") String inStock);

    @FormUrlEncoded
    @POST("Salesexcutive/salesexecutiveCheckin")
    Call<JsonObject> checkin_TimeUpdate(@Field("employee_id") String empId, @Field("shop_id") String shopId, @Field("latitude") String latitude, @Field("longitude") String longitude);

    @GET("Salesexcutive/login")
    Call<JsonObject> loginResponse(@Query("username") String userName, @Query("password")  String password);

}
