package com.example.mydemopersonal.mvpPattern.presenter;


import com.example.mydemopersonal.interfaces.UserActivityView;
import com.example.mydemopersonal.mvpPattern.model.User;

public class UserActivityPresenter {
    User user;
    UserActivityView view;

    public UserActivityPresenter(UserActivityView view) {
        this.user = new User();
        this.view = view;
    }

    public void setUser(String userName, String userEmail){
        user.setUserName(userName);
        user.setEmail(userEmail);

        view.updateUser(user.getUserName(), user.getEmail());
    }
}
