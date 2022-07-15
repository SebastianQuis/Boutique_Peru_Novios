package com.example.dalisaapp.venta.dashclient.categoria.model;

import com.example.dalisaapp.venta.dashclient.Tienda.model.Prenda;
import com.example.dalisaapp.zUtilidad.model.ResponseData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServiFilt {

    @POST("/Prod/listProductFilterByCategory")
    Call<List<Prenda>> filter(@Header("Content-Type") String content, @Body JsonObject jsonObject);

    @POST("/Prod/listCategory")
    Call<List<Categoria>> lister(@Header("Content-Type") String content);

}
