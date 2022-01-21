package com.example.mydemopersonal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityRatingbarBinding;
import com.example.mydemopersonal.databinding.DialogRatingbarBinding;

public class RatingbarActivity extends AppCompatActivity {
    ActivityRatingbarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnShowRatingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogRatingbarBinding binding = DialogRatingbarBinding.inflate(getLayoutInflater());
                Dialog dialog = new Dialog(RatingbarActivity.this);
                dialog.setContentView(binding.getRoot());

                binding.btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(RatingbarActivity.this, ""+binding.ratingBar.getRating(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                binding.btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}