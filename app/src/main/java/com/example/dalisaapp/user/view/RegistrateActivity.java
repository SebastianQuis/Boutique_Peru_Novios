package com.example.dalisaapp.user.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import com.example.dalisaapp.R;
import com.example.dalisaapp.user.model.User;
import com.example.dalisaapp.user.viewmodel.UserViewModel;
import com.example.dalisaapp.zUtilidad.model.ResponseData;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrateActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private UserViewModel userViewModel;
    private EditText txtNombre, txtApellidos, txtTelefono, txtDireccion, txtCorreo, txtClave, txtClaveConf;
    private Button  btnRegistrar;
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrate);
        setView();
    }

    private void setView() {
        userViewModel = new UserViewModel();
        context=this;

        txtNombre = findViewById(R.id.regTxtNombre);
        txtApellidos = findViewById(R.id.regTxtApellidos);
        txtTelefono = findViewById(R.id.regTxtTelefono);
        txtDireccion = findViewById(R.id.regTxtDireccion);
        txtCorreo = findViewById(R.id.regTxtCorreo);
        txtClave = findViewById(R.id.regTxtClave);
        txtClaveConf = findViewById(R.id.regTxtClaveConf);
        btnRegistrar = findViewById(R.id.regBtnRegistrar);
        myToolbar=findViewById(R.id.toolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_white_24);
        myToolbar.setTitle("Regístrate");
        setSupportActionBar(myToolbar);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regBtnRegistrar: onClickRegistrar();break;
        }
    }

    private void onClickRegistrar() {
        if (txtClave.getText().toString().equals(txtClaveConf.getText().toString())){
            User user = new User();
            user.setNombre(txtNombre.getText().toString().trim());
            user.setApellido(txtApellidos.getText().toString().trim());
            user.setTelefono(txtTelefono.getText().toString().trim());
            user.setDireccion(txtDireccion.getText().toString().trim());
            user.setCorreo(txtCorreo.getText().toString().trim());
            user.setPuntos(0);
            user.setPassword(txtClave.getText().toString().trim());
            userViewModel.callSaveUser(user).observe(this, new Observer<ResponseData>() {
                @Override
                public void onChanged(ResponseData responseData) {
                    mensajeRespuesta(responseData.getMesanje());
                }
            });
        } else {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
    }

    private void  mensajeRespuesta(String mensaje) {
        try {
            var builder = new AlertDialog.Builder(this);
            builder.setTitle("Registrado");
            builder.setMessage(mensaje)
                    .setCancelable(true);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    onBackPressed();
                }
            });
            builder.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}