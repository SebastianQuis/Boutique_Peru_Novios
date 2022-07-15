package com.example.dalisaapp.venta.dashclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.dalisaapp.R;
import com.example.dalisaapp.user.view.LyLogin;
import com.example.dalisaapp.venta.dashclient.Tienda.view.fragment.FgTienda;
import com.example.dalisaapp.venta.dashclient.categoria.view.fragment.FgCategoria;
import com.example.dalisaapp.venta.dashclient.cuenta.CuentaFragment;
import com.example.dalisaapp.venta.dashclient.descuento.view.fragment.FgDescuento;
import com.example.dalisaapp.venta.dashclient.pedido.view.fragement.PedidoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LyPrincipalVenta extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView toobarInferior;
    private SharedPreferences sharePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_venta);

        setView();
    }

    private void setView(){
        toobarInferior=findViewById(R.id.naviationVenta);
        sharePreferences = getSharedPreferences("DataUser", Context.MODE_PRIVATE);
        toobarInferior.setOnNavigationItemSelectedListener(this);
        remplaceFragment(new FgTienda());


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemDescuento: remplaceFragment(new FgDescuento());return true;
            case R.id.itemTienda: remplaceFragment(new FgTienda());return true;
            case R.id.itemCategoria: remplaceFragment(new FgCategoria());return true;
            case R.id.itemPedido:
                if(sharePreferences.getBoolean("isLogeado",false)){
                      remplaceFragment(new PedidoFragment());
                }else {
                    Intent intent = new Intent(this, LyLogin.class);
                    startActivity(intent);
                }

                return true;
            case R.id.itemUser:
                if(sharePreferences.getBoolean("isLogeado",false)){
                    remplaceFragment(new CuentaFragment());
                }else {
                    Intent intent = new Intent(this, LyLogin.class);
                    startActivity(intent);
                }
               return true;
            //case R.id.itemUser:    startActivity( new Intent(this, LyLogin.class)); return true;
            default: return false;
        }
    }

    private  void remplaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransient = fragmentManager.beginTransaction();
        fragmentTransient.replace(R.id.idFragementContainer,fragment);
        fragmentTransient.commit();
    }


}