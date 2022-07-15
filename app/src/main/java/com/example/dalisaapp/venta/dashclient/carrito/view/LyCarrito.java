package com.example.dalisaapp.venta.dashclient.carrito.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dalisaapp.R;
import com.example.dalisaapp.user.view.LyLogin;
import com.example.dalisaapp.venta.dashclient.LyPrincipalVenta;
import com.example.dalisaapp.venta.dashclient.carrito.model.Carrito;
import com.example.dalisaapp.venta.dashclient.carrito.view.adpter.CarritoAdapter;
import com.example.dalisaapp.venta.dashclient.pagar.LyPagar;
import com.example.dalisaapp.zUtilidad.SwipeCallbackLeft;
import com.example.dalisaapp.zUtilidad.Util;

import java.text.DecimalFormat;

public class LyCarrito extends AppCompatActivity implements View.OnClickListener {
    private Button btnAgregarProducto, btnPagarPedido;
    private RecyclerView carritoRecyCleView;
    private TextView idSubTotal,idTotalPagar;
    private  CarritoAdapter carritoAdapter;
    private SharedPreferences sharePreferences;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_carrito);
        btnPagarPedido = findViewById(R.id.idPagar);
        btnPagarPedido.setOnClickListener(this);
        setView();
    }

    private void setView(){
        sharePreferences = getSharedPreferences("DataUser", Context.MODE_PRIVATE);
        btnAgregarProducto = findViewById(R.id.btnAgrProducto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        idSubTotal= findViewById(R.id.idSubTotal);
        idTotalPagar= findViewById(R.id.idTotalPagar);
        context=this;
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_white_24);
        toolbar.setTitle("Carrito de Prendas");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        carritoRecyCleView=findViewById(R.id.idCarritoRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        carritoRecyCleView.setLayoutManager(linearLayoutManager);
        carritoRecyCleView.getItemAnimator();
        carritoAdapter=new CarritoAdapter(Util.listaCarritoProducto);
        carritoRecyCleView.setAdapter(carritoAdapter);
        cargarTotal();
        enableSwipeToLeft();
        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LyPrincipalVenta.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.idPagar:
           if(sharePreferences.getBoolean("isLogeado",false)){
                    irPagar();
            }else {
               if(Util.listaCarritoProducto.size()>0){
                   Intent intent = new Intent(this, LyLogin.class);
                   startActivity(intent);
               }else{

                   Toast.makeText(context,"Agrege una Prenda como minimo",Toast.LENGTH_SHORT).show();
               }

            }

                break;
        }
    }
    private void enableSwipeToLeft() {

        SwipeCallbackLeft swipeToDeleteCallback = new SwipeCallbackLeft(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();

                carritoAdapter.removeItem(position);
                cargarTotal();
            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(carritoRecyCleView);
    }

    private void irPagar() {
        Intent iPagar = new Intent(this, LyPagar.class);
        startActivity(iPagar);
    }

    private void cargarTotal(){
        DecimalFormat df = new DecimalFormat("#.##");

        double total=0.0;
        for (int i=0;i<Util.listaCarritoProducto.size();i++){
            Carrito c = Util.listaCarritoProducto.get(i);
             total +=c.getImporteTotal();
        }
        idSubTotal.setText(df.format(total));
        idTotalPagar.setText(df.format((total+5)));
    }
}