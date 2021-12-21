package com.example.mydemopersonal.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mydemopersonal.R;

public class FragActivityInteractionActivity extends AppCompatActivity implements FragmentA.OnInputListener {
    FragmentManager fragmentManager;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_interaction);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragContainer, new FragmentA());
        fragmentTransaction.commit();
        textView = findViewById(R.id.tvResult);

    }


    @Override
    public void input(String input) {
        textView.setText(input);
    }
}