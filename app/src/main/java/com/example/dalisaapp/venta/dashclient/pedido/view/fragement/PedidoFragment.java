package com.example.dalisaapp.venta.dashclient.pedido.view.fragement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dalisaapp.R;
import com.example.dalisaapp.venta.dashclient.pagar.model.CabBoleta;
import com.example.dalisaapp.venta.dashclient.pagar.model.VentaRepositoryImpl;
import com.example.dalisaapp.venta.dashclient.pedido.view.adapter.PedidoAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PedidoFragment extends Fragment {

   private RecyclerView recyclerView;
   private VentaRepositoryImpl ventaRepository = new VentaRepositoryImpl();
    private SharedPreferences sharePreferences;
    Context context;
    public PedidoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_pedido, container, false);
        recyclerView= v.findViewById(R.id.pedidoView);
        context = v.getContext();
        sharePreferences = v.getContext().getSharedPreferences("DataUser", Context.MODE_PRIVATE);
        setLis();
        return  v;
    }

    void setLis(){
        ventaRepository.listBoleta(sharePreferences.getInt("idUser",-1), new Callback<List<CabBoleta>>() {
            @Override
            public void onResponse(Call<List<CabBoleta>> call, Response<List<CabBoleta>> response) {
                PedidoAdapter p  = new PedidoAdapter(response.body());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.getItemAnimator();
                recyclerView.setAdapter(p);
            }

            @Override
            public void onFailure(Call<List<CabBoleta>> call, Throwable t) {

            }
        });
    }
}