package com.example.dalisaapp.venta.dashclient.categoria.view.fragment;

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
import com.example.dalisaapp.venta.dashclient.categoria.model.Categoria;
import com.example.dalisaapp.venta.dashclient.categoria.model.filtrar;
import com.example.dalisaapp.venta.dashclient.categoria.view.LyPorductByCategory;
import com.example.dalisaapp.venta.dashclient.categoria.view.adapter.CategoriaAdapter;
import com.example.dalisaapp.zUtilidad.model.OnItemObjectListenerLey;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FgCategoria extends Fragment implements View.OnClickListener , OnItemObjectListenerLey<Categoria> {


    private RecyclerView categoriaRecyCleView;
    private Context context;
    private TextView btnCesta;
    private TextView btnPerfil;
    private filtrar Cateriasfilt = new filtrar();


    public FgCategoria() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_categoria, container, false);

        setView(view);

        return view;
    }


    private void setView(View view){
        context= view.getContext();
        categoriaRecyCleView = view.findViewById(R.id.idCategoriaReclyview);
        btnCesta = view.findViewById(R.id. btnCesta);
        btnCesta.setOnClickListener(this);
        btnPerfil=view.findViewById(R.id.btnPerfil);
        btnPerfil.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        categoriaRecyCleView.setLayoutManager(linearLayoutManager);
        categoriaRecyCleView.getItemAnimator();
        Cateriasfilt();
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
    @Override
    public void onClickListenerLeyCustom(Categoria categoria) {
        Intent intent=new Intent(context, LyPorductByCategory.class);
        intent.putExtra("categoria", categoria);
        startActivity(intent);
    }

    void Cateriasfilt(){
        Cateriasfilt.cateriaslista(new Callback<List<Categoria>>() {
            @Override
           public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
               if(response.isSuccessful()
               )
               {
                   var adapter=new CategoriaAdapter(response.body());
                   categoriaRecyCleView.setAdapter(adapter);
                   adapter.setOnItemListener(FgCategoria.this);
               }

           }

           @Override
           public void onFailure(Call<List<Categoria>> call, Throwable t) {

           }
       }
        );


    }
}