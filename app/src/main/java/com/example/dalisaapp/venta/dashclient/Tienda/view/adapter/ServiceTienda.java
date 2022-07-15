package com.example.dalisaapp.venta.dashclient.Tienda.view.adapter;

import com.example.dalisaapp.zUtilidad.model.ResponseData;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServiceTienda {

    @POST("/Prod/listadoCateAndProducto")
    Call<ResponseData> saveCab(@Header("Content-Type") String content, @Body JsonObject jsonObject);


}
