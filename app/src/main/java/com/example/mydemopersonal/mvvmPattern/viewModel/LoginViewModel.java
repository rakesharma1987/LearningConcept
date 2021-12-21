package com.example.mydemopersonal.mvvmPattern.viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

//import com.example.mydemopersonal.BR;
import com.example.mydemopersonal.mvvmPattern.model.User;

public class LoginViewModel extends BaseObservable {
    User user;

    @Bindable
    private String toastMessage;

    private void setToastMessage(String msg){
        this.toastMessage = msg;
//        notifyPropertyChanged(BR.toastMessage);
    }

    private String getToastMessage(){
        return toastMessage;
    }

    @Bindable
    private String getUserId(){
        return user.getUserId();
    }

    private void setUserId(String userId){
        user.setUserId(userId);
//        notifyPropertyChanged(BR.userId);
    }

    @Bindable
    private String getPassword(){
        return user.getPassowrd();
    }

    private void setPassword(String password){
        user.setPassowrd(password);
//        notifyPropertyChanged(BR.password);
    }

}
