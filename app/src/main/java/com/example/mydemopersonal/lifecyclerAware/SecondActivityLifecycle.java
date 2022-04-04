package com.example.mydemopersonal.lifecyclerAware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mydemopersonal.R;

public class SecondActivityLifecycle extends AppCompatActivity {
    private String TAG = "lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_lifecycle);
        getLifecycle().addObserver(new ActivityComponent("SecondActivityLifecycle"));
    }
}