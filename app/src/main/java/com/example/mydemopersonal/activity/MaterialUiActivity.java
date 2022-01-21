package com.example.mydemopersonal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityMaterialUiBinding;

public class MaterialUiActivity extends AppCompatActivity {
    ActivityMaterialUiBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMaterialUiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tilName.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MaterialUiActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

        binding.tieName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 10){
                    binding.tilName.setError("No more");
                }else {
                    binding.tilName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}