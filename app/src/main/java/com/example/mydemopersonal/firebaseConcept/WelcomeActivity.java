package com.example.mydemopersonal.firebaseConcept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {
    ActivityWelcomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvEmail.setText("Hello, "+getIntent().getStringExtra("_email"));
    }
}