package com.example.dalisaapp.venta.dashclient.descuento.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dalisaapp.R;
import com.example.dalisaapp.user.view.LyLogin;
import com.example.dalisaapp.venta.dashclient.carrito.view.LyCarrito;
import com.example.dalisaapp.venta.dashclient.descuento.model.Descuento;
import com.example.dalisaapp.venta.dashclient.descuento.view.adapter.DescuentoAdapter;

import java.util.Arrays;

public class FgDescuento extends Fragment implements  View.OnClickListener{

    private RecyclerView recyclerViewDiscount;
    private TextView btnCesta;
    private TextView btnPerfil;
    private Context context;

    public FgDescuento() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_descuento, container, false);
        this.recyclerViewDiscount = v.findViewById(R.id.idDescuentoReclyview);
        btnCesta = v.findViewById(R.id. btnCesta);
        btnCesta.setOnClickListener(this);
        btnPerfil=v.findViewById(R.id.btnPerfil);
        context = v.getContext();
        btnPerfil.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
        this.recyclerViewDiscount .setLayoutManager(linearLayoutManager);
        this.recyclerViewDiscount .getItemAnimator();
        this.recyclerViewDiscount .setAdapter(new DescuentoAdapter(Arrays.
                asList(new Descuento())));
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v == btnPerfil){
            Intent intent = new Intent(context, LyLogin.class);
            startActivity(intent);
        }
        if(v == btnCesta){
            Intent intent = new Intent(context, LyCarrito.class);
            startActivity(intent);
        }
    }



}