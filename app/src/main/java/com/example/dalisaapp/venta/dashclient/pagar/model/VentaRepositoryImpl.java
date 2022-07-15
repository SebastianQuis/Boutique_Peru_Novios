package com.example.dalisaapp.venta.dashclient.pagar.model;

import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;

import com.example.dalisaapp.venta.dashclient.pagar.LyPagar;
import com.example.dalisaapp.zUtilidad.model.ResponseData;
import com.example.dalisaapp.zUtilidad.service.ServicioGenerador;
import com.example.dalisaapp.zUtilidad.service.Servidor;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VentaRepositoryImpl {

    private MutableLiveData<ResponseData> responseData = new MutableLiveData<>();
    private MutableLiveData<String> fail = new MutableLiveData<>();


    public MutableLiveData<ResponseData> saveCab(Integer idUser, String tipoPago, double impoTotal) {
        var obj = new JsonObject();
        obj.addProperty("idUsuario", idUser);
        obj.addProperty("fechaGenerada", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
        obj.addProperty("estado", "Pendiente");
        obj.addProperty("tipoPago", tipoPago);
        obj.addProperty("fechaEntrada", "");
        obj.addProperty("importeTotal", impoTotal);
        obj.addProperty("idRepartidor", 1);
        var call = ServicioGenerador.createService(ServiceVenta.class, Servidor.SERVICIO_REST_SPRING);
        var saveData = call.saveCab(Servidor.CONTENT, obj);
        saveData.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    responseData.setValue(response.body());
                } else {
                    responseData.setValue(new ResponseData(false, response.message()));

                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                responseData.setValue(new ResponseData(false, t.getMessage()));

            }
        });

        return responseData;
    }


    public void saveDet(List<DetBoleta> list,Callback<ResponseData> callBak) {
        var json = new JsonArray();
        try{
            for (DetBoleta d :list ) {
                var obj = new JsonObject();
                obj.addProperty("idProducto", d.getIdProducto());
                obj.addProperty("precio",d.getPrecio());
                obj.addProperty("descuento", d.getDescuento());
                obj.addProperty("caqntidad", d.getCaqntidad());
                obj.addProperty("punto", d.getPunto());
                obj.addProperty("importePagar", d.getImportePagar());
                obj.addProperty("idCabecera", d.getIdCabecera());
                json.add(obj);
            }

        }catch (Exception e){

        }

        var call = ServicioGenerador.createService(ServiceVenta.class, Servidor.SERVICIO_REST_SPRING);
        var saveData = call.saveDet(Servidor.CONTENT, json);
        saveData.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                callBak.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                responseData.setValue(new ResponseData(false, t.getMessage()));
                callBak.onFailure(call,t);
            }
        });

    }

    public void listBoleta(Integer id,Callback<List<CabBoleta>> callBak) {
        var obj = new JsonObject();
        obj.addProperty("id", id);

        var call = ServicioGenerador.createService(ServiceVenta.class, Servidor.SERVICIO_REST_SPRING);
        var saveData = call.list(Servidor.CONTENT, obj);
        saveData.enqueue(new Callback<List<CabBoleta>>() {
            @Override
            public void onResponse(Call<List<CabBoleta>> call, Response<List<CabBoleta>> response) {
                callBak.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<List<CabBoleta>> call, Throwable t) {
                responseData.setValue(new ResponseData(false, t.getMessage()));
                callBak.onFailure(call,t);
            }
        });

    }
}
