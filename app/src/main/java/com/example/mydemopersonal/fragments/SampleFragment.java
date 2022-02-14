package com.example.mydemopersonal.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydemopersonal.R;

public class SampleFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG:", "Fragment onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("TAG:", "Fragment onCreateView()");
        return inflater.inflate(R.layout.fragment_sample, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TAG:", "Fragment onViewCreated()");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("TAG:", "Fragment onViewStateRestored()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TAG:", "Fragment onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG:", "Fragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TAG:", "Fragment onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TAG:", "Fragment onStop()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("TAG:", "Fragment onSaveInstanceState()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TAG:", "Fragment onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG:", "Fragment onDestroy()");
    }
}