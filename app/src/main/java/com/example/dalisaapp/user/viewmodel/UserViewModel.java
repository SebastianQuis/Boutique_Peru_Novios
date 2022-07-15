package com.example.dalisaapp.user.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dalisaapp.user.model.User;
import com.example.dalisaapp.user.model.UserObservable;
import com.example.dalisaapp.zUtilidad.model.ResponseData;

public class UserViewModel extends ViewModel {

    private UserObservable userObservable = new UserObservable();


    public MutableLiveData<User> callSignUser(String userName, String password) {
        return userObservable.wsSingUser(userName,password);
    }
    public MutableLiveData<ResponseData> callSaveUser(User user) {
        return userObservable.wsSaveUser(user);
    }
    public MutableLiveData<String> callFail() {
        return userObservable.wsFail();
    }
}
