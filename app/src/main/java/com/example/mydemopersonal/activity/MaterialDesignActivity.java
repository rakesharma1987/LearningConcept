package com.example.mydemopersonal.activity;

import android.os.Bundle;
import android.view.View;

import com.example.mydemopersonal.databinding.ActivityMaterialDesignBinding;

public class MaterialDesignActivity extends BaseActivity {
    private ActivityMaterialDesignBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMaterialDesignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tilName.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("clicked");
            }
        });


        binding.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.tieName.getEditableText().toString().length() < 10){
                    binding.tilName.setError("Invalid name");
                }else {
                    binding.tilName.setErrorEnabled(false);
                }
            }
        });


    }
}