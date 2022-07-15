package com.example.dalisaapp.venta.dashclient.Tienda.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dalisaapp.R;
import com.example.dalisaapp.user.view.LyLogin;
import com.example.dalisaapp.venta.dashclient.Tienda.model.Prenda;
import com.example.dalisaapp.venta.dashclient.Tienda.model.Producto;
import com.example.dalisaapp.venta.dashclient.Tienda.model.ProductoImplement;
import com.example.dalisaapp.venta.dashclient.Tienda.view.adapter.ProductoAdapter;
import com.example.dalisaapp.venta.dashclient.carrito.model.Carrito;
import com.example.dalisaapp.venta.dashclient.carrito.view.LyCarrito;
import com.example.dalisaapp.zUtilidad.Util;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FgTienda extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, ProductoAdapter.ItemPrendaSelect{



    private RadioGroup radio_group;
    private RecyclerView reclyeView;
    private TextView btnPerfil;
    private TextView btnCesta;
    private Context context;
    private ProductoImplement productoImplement = new ProductoImplement();
    private View view;

    public FgTienda() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_blank, container, false);
        setView(view);
        return view;

    }
    public void openCarrito(View view){
        startActivity( new Intent(context,LyCarrito.class));
    }


    private void setView(View view){
        context=view.getContext();
        this.view=view;
        remplaceFragment(new FgPromocion("Promocio 1","Promocion 2"));
        radio_group=view.findViewById(R.id.radio_group);
        reclyeView =view.findViewById(R.id.recyProducto);
        radio_group.setOnCheckedChangeListener(this);
        btnCesta = view.findViewById(R.id. btnCesta);
        btnCesta.setOnClickListener(this);
        btnPerfil=view.findViewById(R.id.btnPerfil);
        btnPerfil.setOnClickListener(this);
        cargarData();

    }
    void cargarData(){
        productoImplement.ListadoPrenda(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                 if(response.isSuccessful()){
                     ProductoAdapter productoAdapter = new ProductoAdapter(response.body());
                     LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                     reclyeView.setLayoutManager(linearLayoutManager);
                     reclyeView.getItemAnimator();
                     reclyeView.setAdapter(productoAdapter);
                     productoAdapter.setItemPrendaSelect(FgTienda.this);
                 }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == btnPerfil){
            Intent intent = new Intent(context, LyLogin.class);
            startActivity(intent);
        }
        if(v == btnCesta){
            Intent intent = new Intent(context,LyCarrito.class);
            startActivity(intent);
        }
    }


    private  void remplaceFragment(Fragment fragment){
        var  myContext=(FragmentActivity) this.context;
        FragmentManager fragmentManager =myContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransient = fragmentManager.beginTransaction();
        fragmentTransient.replace(R.id.fragment_main,fragment);
        fragmentTransient.commit();

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int idRadio) {
        switch (idRadio) {
            case R.id.group_one:
                remplaceFragment(new FgPromocion("Promocio 1","Promocion 2"));
                break;
            case R.id.group_two:
                remplaceFragment(new FgPromocion("Promocio 3","Promocion 4"));
                break;
            case R.id.group_tree:
                remplaceFragment(new FgPromocion("Promocio 5","Promocion 6"));
                break;
            case R.id.group_for:
                remplaceFragment(new FgPromocion("Promocio 7","Promocion 8"));
                break;
            case R.id.group_five:
                remplaceFragment(new FgPromocion("Promocio 9","Promocion 10"));
                break;
            case R.id.group_sex:
                remplaceFragment(new FgPromocion("Promocio 11","Promocion 12"));
                break;

        }
    }

    @Override
    public void onclickPrenda(Prenda prenda) {
        boolean existe=false;
        for (int i=0;i<Util.listaCarritoProducto.size();i++){
            Carrito c=Util.listaCarritoProducto.get(i);
            if(c.getPrenda().getId() == prenda.getId()){
                c.setCantidad(c.getCantidad()+1);
                Util.listaCarritoProducto.set(i,c);
                existe=true;break;
            }
        }
        if(!existe){
            Carrito c= new Carrito();
            c.setId(Util.listaCarritoProducto.size());
            c.setPrecio(prenda.getPrecio());
            c.setCantidad(1);
            c.setPrenda(prenda);
            Util.listaCarritoProducto.add(c);
        }
        Toast.makeText(this.context,"Se agrego al carrito",Toast.LENGTH_SHORT).show();
    }


}