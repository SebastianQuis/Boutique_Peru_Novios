package com.example.dalisaapp.venta.dashclient.pagar.model;


import com.example.dalisaapp.zUtilidad.model.ResponseData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ServiceVenta {


    @POST("/User/saveCab")
    Call<ResponseData> saveCab(@Header("Content-Type") String content, @Body JsonObject jsonObject);

    @POST("/User/saveDet")
    Call<ResponseData> saveDet(@Header("Content-Type") String content, @Body JsonArray jsonArray);

    @POST("/User/listVenta")
    Call<List<CabBoleta>> list(@Header("Content-Type") String content, @Body JsonObject jsonObject);


}
