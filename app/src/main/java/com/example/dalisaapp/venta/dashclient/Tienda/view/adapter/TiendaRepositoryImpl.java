package com.example.dalisaapp.venta.dashclient.Tienda.view.adapter;

import androidx.lifecycle.MutableLiveData;

import com.example.dalisaapp.venta.dashclient.pagar.model.ServiceVenta;
import com.example.dalisaapp.zUtilidad.model.ResponseData;
import com.example.dalisaapp.zUtilidad.service.ServicioGenerador;
import com.example.dalisaapp.zUtilidad.service.Servidor;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TiendaRepositoryImpl {
    private MutableLiveData<ResponseData> responseData = new MutableLiveData<>();
    private MutableLiveData<String> fail = new MutableLiveData<>();

    public MutableLiveData<ResponseData> listProduct(String idProduct, String nomProduct, String fotoProduct) {
        var obj = new JsonObject();
        obj.addProperty("nombre", idProduct);
        obj.addProperty("precio", nomProduct);
        obj.addProperty("foto", fotoProduct);

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

}
