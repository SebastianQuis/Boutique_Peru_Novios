package com.example.dalisaapp.user.model;

import com.example.dalisaapp.zUtilidad.model.ResponseData;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    @POST("/User/sign")
    Call<User> loginUser(@Header("Content-Type") String content, @Body JsonObject jsonObject);
    @POST("/User/saveUser")
    Call<ResponseData> saveUser(@Header("Content-Type") String content, @Body JsonObject jsonObject);

}
