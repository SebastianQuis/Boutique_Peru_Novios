package com.example.dalisaapp.venta.dashclient.Tienda.model;

import com.example.dalisaapp.venta.dashclient.pagar.model.CabBoleta;
import com.example.dalisaapp.venta.dashclient.pagar.model.ServiceVenta;
import com.example.dalisaapp.zUtilidad.model.ResponseData;
import com.example.dalisaapp.zUtilidad.service.ServicioGenerador;
import com.example.dalisaapp.zUtilidad.service.Servidor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoImplement {




   public void ListadoPrenda(Callback<List<Producto>> listCallback){

        var call = ServicioGenerador.createService(ProductoService.class, Servidor.SERVICIO_REST_SPRING);
        var saveData = call.listadoProductoTienda(Servidor.CONTENT);
        saveData.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                listCallback.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                listCallback.onFailure(call,t);
            }
        });
    }
}
