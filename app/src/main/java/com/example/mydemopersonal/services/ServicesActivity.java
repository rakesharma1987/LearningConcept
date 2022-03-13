package com.example.mydemopersonal.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityServicesBinding;

public class ServicesActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityServicesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServicesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStartClassicService.setOnClickListener(this);
        binding.btnJobIntentService.setOnClickListener(this);
        binding.btnStopService.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start_classic_service:
                Intent intent = new Intent(ServicesActivity.this, ServiceExample.class);
                startService(intent);

                break;
            case R.id.btn_job_intent_service:
                Intent intent1 = new Intent(ServicesActivity.this, ServiceExample.class);
                JobIntentServiceExample.myBackgroundService(ServicesActivity.this, intent1);
                break;
            case R.id.btn_stop_service:
                Intent intentStop = new Intent(ServicesActivity.this, ServiceExample.class);
                stopService(intentStop);
                break;
        }
    }
}