package com.example.dalisaapp.venta.dashclient.categoria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dalisaapp.R;
import com.example.dalisaapp.venta.dashclient.Tienda.model.Prenda;
import com.example.dalisaapp.venta.dashclient.Tienda.view.adapter.ProductoAdapter;
import com.example.dalisaapp.venta.dashclient.carrito.model.Carrito;
import com.example.dalisaapp.venta.dashclient.categoria.model.Categoria;
import com.example.dalisaapp.venta.dashclient.categoria.model.filtrar;
import com.example.dalisaapp.venta.dashclient.categoria.view.adapter.CategoriaAdapter;
import com.example.dalisaapp.venta.dashclient.categoria.view.adapter.Viewimagenes;
import com.example.dalisaapp.zUtilidad.Util;
import com.example.dalisaapp.zUtilidad.model.OnItemObjectListenerLey;
import com.google.android.material.appbar.AppBarLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LyPorductByCategory extends AppCompatActivity implements OnItemObjectListenerLey<Prenda> {

    private Categoria categoria = new Categoria("Not Title");

    private filtrar filter = new filtrar();
    private RecyclerView reclyeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_porduct_by_category);

        setView();
    }

    private void setView(){
        var extras = getIntent().getExtras();
        Toolbar myToolbar = findViewById(R.id.toolbar);
        reclyeView =findViewById(R.id.listasview);
        if (extras !=null){
            categoria =(Categoria) extras.getSerializable("categoria");
        }

        myToolbar.setTitle(categoria.getNameCategoria().toUpperCase());
        setSupportActionBar(myToolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_white_24);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        listfiltro();
    }

    void listfiltro (){
        filter.listfilt(categoria.getId(), new Callback<List<Prenda>>() {
                    @Override
                    public void onResponse(Call<List<Prenda>> call, Response<List<Prenda>> response) {
                        if(response.isSuccessful()){
                             Viewimagenes viewimagenes = new Viewimagenes(response.body());
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LyPorductByCategory.this);
                            reclyeView.setLayoutManager(linearLayoutManager);
                            reclyeView.getItemAnimator();
                            reclyeView.setAdapter(viewimagenes);
                            viewimagenes.setOnItemObjectListenerLey(LyPorductByCategory.this);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Prenda>> call, Throwable t) {

                    }
                }

        );
    }

    @Override
    public void onClickListenerLeyCustom(Prenda prenda) {
        boolean existe=false;
        for (int i = 0; i< Util.listaCarritoProducto.size(); i++){
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
        Toast.makeText(this,"Se agrego al carrito",Toast.LENGTH_SHORT).show();
    }
}