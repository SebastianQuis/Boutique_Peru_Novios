package com.example.dalisaapp.user.model;

import androidx.lifecycle.MutableLiveData;

import com.example.dalisaapp.zUtilidad.model.ResponseData;
import com.example.dalisaapp.zUtilidad.service.ServicioGenerador;
import com.example.dalisaapp.zUtilidad.service.Servidor;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements  UserRepository{

    private MutableLiveData<User> UserData = new MutableLiveData<>();
    private MutableLiveData<ResponseData> responseData = new MutableLiveData<>();
    private MutableLiveData<String> fail = new MutableLiveData<>();

    @Override
    public MutableLiveData<User> signUser(String userName, String password) {
       var obj = new JsonObject();
       obj.addProperty("userName",userName);
        obj.addProperty("password",password);

        var call = ServicioGenerador.createService(UserService.class, Servidor.SERVICIO_REST_SPRING);

       var loginAcesso = call.loginUser(Servidor.CONTENT,obj);
       loginAcesso.enqueue(new Callback<User>() {
           @Override
           public void onResponse(Call<User> call, Response<User> response) {
               if(response.isSuccessful()){
                   System.out.println(response.body());
                   UserData.setValue(response.body());
               }else {
                   UserData.setValue(null);
               }
           }
           @Override
           public void onFailure(Call<User> call, Throwable t) {
               UserData.setValue(null);
               fail.setValue(t.getMessage());
           }
       });

        return UserData;
    }

    @Override
    public MutableLiveData<ResponseData> saveUser(User use) {
        var obj = new JsonObject();
        obj.addProperty("nombre",use.getNombre());
        obj.addProperty("apellido",use.getApellido());
        obj.addProperty("telefono",use.getTelefono());
        obj.addProperty("correo",use.getCorreo());
        obj.addProperty("puntos",use.getPuntos());
        obj.addProperty("userName",use.getUserName());
        obj.addProperty("password",use.getPassword());
        obj.addProperty("direccion",use.getDireccion());
        var call = ServicioGenerador.createService(UserService.class, Servidor.SERVICIO_REST_SPRING);

        var saveUser = call.saveUser(Servidor.CONTENT,obj);
        saveUser.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()){
                    System.out.println(response.body());
                    responseData.setValue(response.body());
                }else {
                    responseData.setValue(new ResponseData(false,response.message()));
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                responseData.setValue(new ResponseData(false,t.getMessage()));
                fail.setValue(t.getMessage());
            }
        });

        return responseData;
    }

    @Override
    public MutableLiveData<String> fail() {
        return fail;
    }


}
