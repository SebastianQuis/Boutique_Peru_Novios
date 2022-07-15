package com.example.dalisaapp.user.model;

import androidx.lifecycle.MutableLiveData;

import com.example.dalisaapp.zUtilidad.model.ResponseData;

public interface UserRepository {


    MutableLiveData<User> signUser(String userName, String password);
    MutableLiveData<ResponseData> saveUser(User use);
    MutableLiveData<String> fail();
}
