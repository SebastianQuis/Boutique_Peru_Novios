package com.example.dalisaapp.venta.dashclient.cuenta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dalisaapp.R;
import com.example.dalisaapp.user.view.LyLogin;
import com.example.dalisaapp.venta.dashclient.LyPrincipalVenta;
import com.example.dalisaapp.venta.dashclient.categoria.model.Categoria;
import com.example.dalisaapp.venta.dashclient.categoria.view.adapter.CategoriaAdapter;

import java.util.Arrays;


public class CuentaFragment extends Fragment implements View.OnClickListener{
    TextView txtNombre, txtTelefono, txtDirecciones, txtCerrarSesion;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private TextView txtNombrePopUp, txtTelefonoPopUp, txtDireccionPopUp;
    private Button btnGuardarPopUp, btnSalirPopUp;
    private CheckBox chkTelefono;
    private EditText txtNuevoTelefonoPopUp;
    private SharedPreferences sharePreferences;
    private Context context;
    private RecyclerView recycle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View configuracion = inflater.inflate(R.layout.fragment_cuenta, container, false);
        txtNombre = configuracion.findViewById(R.id.idNombreCuenta);
        txtTelefono = configuracion.findViewById(R.id.idTelefonoCuenta);
        txtDirecciones = configuracion.findViewById(R.id.idDireccionesCuenta);
        txtCerrarSesion = configuracion.findViewById(R.id.frgCtaCerrarSesion);
        context= this.getContext();
        sharePreferences = configuracion.getContext().getSharedPreferences("DataUser", Context.MODE_PRIVATE);
        txtNombre.setOnClickListener(this);
        txtTelefono.setOnClickListener(this);
        txtDirecciones.setOnClickListener(this);
        txtCerrarSesion.setOnClickListener(this);

        return configuracion;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.idNombreCuenta:
                nombreCompleto();
                break;
            case R.id.idTelefonoCuenta:
                telefono();
                break;
            case R.id.idDireccionesCuenta:
                direccion();
                break;
            case R.id.frgCtaCerrarSesion:
                cerrarSesion();
                break;
        }
    }

    private void cerrarSesion() {
        sharePreferences.edit().remove("isLogeado").apply();
        sharePreferences.edit().remove("idUser").apply();
        Intent intent = new Intent(this.context, LyPrincipalVenta.class);
        startActivity(intent);

    }

    private void direccion() {
        dialogBuilder = new AlertDialog.Builder(getContext());
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popupdirecciones, null);

        txtDireccionPopUp = (TextView) contactPopupView.findViewById(R.id.idDireccionesCuenta);
        btnGuardarPopUp = (Button) contactPopupView.findViewById(R.id.btnAceptar);
        String direcc = sharePreferences.getString("direccion","-1");
        txtDireccionPopUp.setText(direcc);


        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        btnGuardarPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void telefono() {
        dialogBuilder = new AlertDialog.Builder(getContext());
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popuptelefono, null);

        txtTelefonoPopUp = (TextView) contactPopupView.findViewById(R.id.txtTelefono);
        chkTelefono = (CheckBox) contactPopupView.findViewById(R.id.chkTelefono);
        txtNuevoTelefonoPopUp = (EditText) contactPopupView.findViewById(R.id.txtNombreUsuarioEditar);
        btnGuardarPopUp = (Button)  contactPopupView.findViewById(R.id.btnAceptar);
        btnSalirPopUp = (Button)  contactPopupView.findViewById(R.id.btnSalir);
        String telef = sharePreferences.getString("telefono","");
        txtTelefonoPopUp.setText(telef);
        txtNuevoTelefonoPopUp.setEnabled(false);

        chkTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.chkTelefono:
                        estadoTelefono(chkTelefono.isChecked());
                        break;
                }
            }
        });

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        btnGuardarPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if txtNuevoTelefonoPopUp
                //code - llamar servicio safe
            }
        });

        btnSalirPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void estadoTelefono(boolean cambiarNumero) {
        txtNuevoTelefonoPopUp.setEnabled(cambiarNumero);
    }

    private void nombreCompleto() {
        dialogBuilder = new AlertDialog.Builder(getContext());
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popupcuenta, null);

        txtNombrePopUp = (TextView) contactPopupView.findViewById(R.id.txtNombreUsuario);
        btnGuardarPopUp = (Button) contactPopupView.findViewById(R.id.btnAceptar);
        String nom = sharePreferences.getString("nombre","-1");
        txtNombrePopUp.setText(nom);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        btnGuardarPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}