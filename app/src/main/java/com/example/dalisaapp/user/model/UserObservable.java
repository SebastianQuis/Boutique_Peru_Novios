package com.example.dalisaapp.user.model;

import androidx.lifecycle.MutableLiveData;

import com.example.dalisaapp.zUtilidad.model.ResponseData;

public class UserObservable {

    private UserRepository userRepository = new UserRepositoryImpl();


    public MutableLiveData<User> wsSingUser(String userName, String password) {
        return userRepository.signUser(userName,password);
    }
    public MutableLiveData<ResponseData> wsSaveUser(User user) {
        return userRepository.saveUser(user);
    }
    public MutableLiveData<String> wsFail() {
        return userRepository.fail();
    }
}
