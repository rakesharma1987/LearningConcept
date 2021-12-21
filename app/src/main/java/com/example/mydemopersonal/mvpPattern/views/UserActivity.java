package com.example.mydemopersonal.mvpPattern.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityUser2Binding;
import com.example.mydemopersonal.interfaces.UserActivityView;
import com.example.mydemopersonal.mvpPattern.model.User;
import com.example.mydemopersonal.mvpPattern.presenter.UserActivityPresenter;

public class UserActivity extends AppCompatActivity implements UserActivityView {
    private UserActivityPresenter userActivityPresenter;
    private ActivityUser2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUser2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        userActivityPresenter = new UserActivityPresenter(this);

        binding.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userActivityPresenter.setUser(binding.etName.getText().toString(), binding.etEmail.getText().toString());
            }
        });

    }

    @Override
    public void updateUser(String name, String email) {
        Toast.makeText(this, name+"\n"+email, Toast.LENGTH_SHORT).show();
    }
}