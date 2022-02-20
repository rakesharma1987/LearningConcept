package com.example.mydemopersonal.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mydemopersonal.databinding.ActivityImpliciteIntentBinding;


public class ImpliciteIntentActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityImpliciteIntentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImpliciteIntentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnImplicit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //1. ACTION_VIEW
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/guide/components/intents-filters"));
//        intent.setData(Uri.parse("https://developer.android.com/"));
//        startActivity(intent);

        // 2. Action_Dial

//        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9625033237"));
//        startActivity(intent);

        // 3. ACTION_CALL

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:9625033237"));

        if (ContextCompat.checkSelfPermission(ImpliciteIntentActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            startActivity(intent);
        }else {
            ActivityCompat.requestPermissions(ImpliciteIntentActivity.this, new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

        }else {

        }
    }
}