package com.example.dalisaapp.venta.dashclient.pagar.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dalisaapp.venta.dashclient.pagar.model.DetBoleta;
import com.example.dalisaapp.venta.dashclient.pagar.model.VentaRepositoryImpl;
import com.example.dalisaapp.zUtilidad.model.ResponseData;

import java.util.List;

import retrofit2.Callback;

public class VentaModel extends ViewModel {

    private VentaRepositoryImpl ventaRepository = new VentaRepositoryImpl();

    public MutableLiveData<ResponseData> saveCab(Integer idUser, String tipoPago, double impoTotal) {
        return  ventaRepository.saveCab(idUser,tipoPago,impoTotal);
    }
    public void saveDet(List<DetBoleta> list, Callback<ResponseData> callBak) {
          ventaRepository.saveDet(list,callBak);
    }

}
