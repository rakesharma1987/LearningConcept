package com.example.mydemopersonal.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.mydemopersonal.R;

public class FragmentLifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_lifecycle);
        Log.d("TAG:", "Activity onCreate()");

        //TODO : Dynamic fragment addition
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_layout, new SampleFragment());
        transaction.commit();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("TAG:", "Activity onSaveInstanceState()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG:", "Activity onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG:", "Activity onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG:", "Activity onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG:", "Activity onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG:", "Activity onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG:", "Activity onDestroy()");
    }
}