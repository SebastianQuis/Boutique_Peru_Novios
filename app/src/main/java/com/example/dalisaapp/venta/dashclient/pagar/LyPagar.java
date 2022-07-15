package com.example.dalisaapp.venta.dashclient.pagar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dalisaapp.R;
import com.example.dalisaapp.venta.dashclient.LyPrincipalVenta;
import com.example.dalisaapp.venta.dashclient.carrito.model.Carrito;
import com.example.dalisaapp.venta.dashclient.carrito.view.adpter.CarritoAdapter;
import com.example.dalisaapp.venta.dashclient.pagar.model.DetBoleta;
import com.example.dalisaapp.venta.dashclient.pagar.model.VentaRepositoryImpl;
import com.example.dalisaapp.zUtilidad.Util;
import com.example.dalisaapp.zUtilidad.model.ResponseData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LyPagar extends AppCompatActivity {
    private RecyclerView carritoRecyCleView;
    private TextView idSubTotal,idTotalPagar,txtDireccion;
    private SharedPreferences sharePreferences;
    private Button btnPagar;
    private CheckBox checkBox;
    private RadioGroup radioGroup;
    private VentaRepositoryImpl ventaRepository;
    private  Context context;
    String tipoPago="Efectivo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ly_pagar);
        setView();

    }
   private void setView(){
       Toolbar toolbar = findViewById(R.id.toolbar);
       sharePreferences = getSharedPreferences("DataUser", Context.MODE_PRIVATE);
       idSubTotal= findViewById(R.id.idSubTotal);
       idTotalPagar= findViewById(R.id.idTotalPagar);
       txtDireccion= findViewById(R.id.idDireccion);
       btnPagar = findViewById(R.id.Pagar);
       checkBox= findViewById(R.id.regChkTerminos);
       radioGroup = findViewById(R.id.regRgrTipoPago);
       context= this.context;
       ventaRepository = new VentaRepositoryImpl();
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
       carritoRecyCleView.setAdapter(new CarritoAdapter(Util.listaCarritoProducto));
       txtDireccion.setText(sharePreferences.getString("Direccion","Registrar su Direccion").toUpperCase());
       cargarTotal();
       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup radioGroup, int i) {
              switch (i){
                  case 2131296672:tipoPago="Efectivo";break;
                  case 2131296673:tipoPago="Tarjeta";break;
                  default: tipoPago="Otros";break;
              }
           }
       });
       btnPagar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(txtDireccion.getText().toString().trim().length()>0){
                   if(checkBox.isChecked()){
                       Integer id = sharePreferences.getInt("idUser",-1);
                       ventaRepository.saveCab(id,tipoPago,Double.parseDouble(idTotalPagar.getText().toString())).observe(LyPagar.this, new Observer<ResponseData>() {
                           @Override
                           public void onChanged(ResponseData responseData) {
                               if(responseData.isStatus()){

                                   List<DetBoleta> listaBoleta = new ArrayList<>();
                                   for (int i=0;i<Util.listaCarritoProducto.size();i++){
                                       Carrito c = Util.listaCarritoProducto.get(i);
                                         DetBoleta d = new DetBoleta();
                                         System.out.println(Integer.parseInt(responseData.getMesanje()));
                                         d.setIdCabecera(Integer.parseInt(responseData.getMesanje()));
                                         d.setCaqntidad(c.getCantidad());
                                         d.setImportePagar(c.getImporteTotal());
                                         d.setIdProducto(c.getPrenda().getId());
                                         d.setPunto(0);
                                         d.setPrecio(c.getPrecio());
                                         listaBoleta.add(d);
                                   }
                                   ventaRepository.saveDet(listaBoleta, new Callback<ResponseData>() {
                                       @Override
                                       public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                                           if(response.isSuccessful()){
                                               Util.listaCarritoProducto=new ArrayList<>();
                                               mensajeRespuesta(response.body().getMesanje());
                                           }else{
                                               mensajeRespuesta("Error al Generar Pedido");
                                           }
                                       }

                                       @Override
                                       public void onFailure(Call<ResponseData> call, Throwable t) {
                                           Toast.makeText(LyPagar.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                       }
                                   });
                               }
                           }
                       });
                   }else{
                          Toast.makeText(LyPagar.this,"Aceptar los terminos", Toast.LENGTH_SHORT).show();
                   }


               }else{
                   Toast.makeText(LyPagar.this, "Registre primero su direccion", Toast.LENGTH_SHORT).show();
               }
           }
       });
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

    private void  mensajeRespuesta(String mensaje) {
        try {
            var builder = new AlertDialog.Builder(this);
            builder.setTitle("Registro de Pedido");
            builder.setMessage(mensaje)
                    .setCancelable(true);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(LyPagar.this.context,LyPrincipalVenta.class));
                  finish();
                }
            });
            builder.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}