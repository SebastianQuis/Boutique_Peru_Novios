package com.example.dalisaapp.venta.dashclient.Tienda.model;

import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ProductoService {


  @POST("/Prod/listadoCateAndProducto")
    Call<List<Producto>> listadoProductoTienda(@Header("Content-Type") String content);
}
