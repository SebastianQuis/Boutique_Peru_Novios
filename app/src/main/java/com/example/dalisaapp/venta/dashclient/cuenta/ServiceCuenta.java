package com.example.dalisaapp.venta.dashclient.cuenta;

import com.example.dalisaapp.zUtilidad.model.ResponseData;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServiceCuenta {

        @POST("/User/saveCab")
    Call<ResponseData> saveCab(@Header("Content-Type") String content, @Body JsonObject jsonObject);

}



