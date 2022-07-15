package com.example.dalisaapp.user.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dalisaapp.R;
import com.example.dalisaapp.user.model.User;
import com.example.dalisaapp.user.viewmodel.UserViewModel;
import com.example.dalisaapp.venta.dashclient.LyPrincipalVenta;

public class LyLogin extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnLogear;
    private UserViewModel userViewModel;
    private EditText txtUserName;
    private EditText txtPassword;
    private TextView lblError;
    private TextView txtRegistrate;
    private Toolbar myToolbar;
    private Context context;
    private SharedPreferences sharePreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setView();
    }

    void setView(){
        userViewModel = new UserViewModel();
        sharePreferences = getSharedPreferences("DataUser",Context.MODE_PRIVATE);
        context=this;
        btnLogear = findViewById(R.id.btnLogear);
        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        lblError = findViewById(R.id.lblError);
        txtRegistrate = findViewById(R.id.logTxtRegistrate);
        btnLogear.setOnClickListener(this);
        myToolbar=findViewById(R.id.toolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_white_24);
        myToolbar.setTitle("Iniciar sesión");
        setSupportActionBar(myToolbar);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        txtRegistrate.setOnClickListener(this);

    }

    private void onClickBtnLogear() {
        userViewModel.callSignUser(txtUserName.getText().toString().trim(),txtPassword.getText().toString().trim()).observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user != null){
                    lblError.setText("Success");
                    sharePreferences.edit().putBoolean("isLogeado",true).apply();
                    sharePreferences.edit().putInt("idUser",user.getId()).apply();
                    sharePreferences.edit().putString("Direccion",user.getDireccion()).apply();
                    sharePreferences.edit().putString("telefono",user.getTelefono()).apply();
                    sharePreferences.edit().putString("correo",user.getCorreo()).apply();
                    sharePreferences.edit().putString("apellido",user.getApellido()).apply();
                    sharePreferences.edit().putString("nombre",user.getNombre()).apply();
                    sharePreferences.edit().putInt("punto",user.getPuntos()).apply();
                    lblError.setTextColor(Color.rgb(181,230,29));
                    Intent intent = new Intent(context, LyPrincipalVenta.class);
                    startActivity(intent);
                    finish();
                }else{
                    lblError.setText("User y/o Password Incorrecto");
                    lblError.setTextColor(Color.rgb(237,28,36));
                }
            }
        });
        userViewModel.callFail().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                System.out.println("error: "+s);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogear: onClickBtnLogear();break;
            case R.id.logTxtRegistrate: onClickRegistrate();break;
        }
    }

    private void onClickRegistrate() {
        Intent iRegistrate = new Intent(context, RegistrateActivity.class);
        startActivity(iRegistrate);
    }
}