package com.example.mydemopersonal.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityNotificationTapBinding;

public class DetailsActivity extends AppCompatActivity {
    private ActivityNotificationTapBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification_tap);
        String discription = getIntent().getStringExtra("notification_data");
        binding.tvNotificationDes.setText(discription);
    }
}