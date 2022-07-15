package com.example.dalisaapp.venta.dashclient.categoria.model;

import com.example.dalisaapp.venta.dashclient.Tienda.model.Prenda;
import com.example.dalisaapp.venta.dashclient.categoria.model.ServiFilt;
import com.example.dalisaapp.zUtilidad.service.ServicioGenerador;
import com.example.dalisaapp.zUtilidad.service.Servidor;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class filtrar {

    public void listfilt(Integer id,Callback<List<Prenda>> callback) {

        var obj = new JsonObject();

        obj.addProperty("id",id);
        var call = ServicioGenerador.createService(ServiFilt.class, Servidor.SERVICIO_REST_SPRING);

        var saveData = call.filter(Servidor.CONTENT, obj);
        saveData.enqueue(new Callback<List<Prenda>>() {
            @Override
            public void onResponse(Call<List<Prenda>> call, Response<List<Prenda>> response) {
            callback.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<List<Prenda>> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });

    }

    public void cateriaslista(Callback<List<Categoria>> callback) {

        var call = ServicioGenerador.createService(ServiFilt.class, Servidor.SERVICIO_REST_SPRING);

        var saveData = call.lister(Servidor.CONTENT);
        saveData.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                callback.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });

    }
}
