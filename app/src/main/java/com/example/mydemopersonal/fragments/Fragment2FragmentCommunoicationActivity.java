package com.example.mydemopersonal.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.mydemopersonal.R;

public class Fragment2FragmentCommunoicationActivity extends AppCompatActivity implements FragmentSend.OnMessageSendListener {
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment2_fragment_communoication);
        FragmentSend fragmentSend = new FragmentSend();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragContainer, fragmentSend);
        fragmentTransaction.commit();

    }

    @Override
    public void onMessageSend(String msg) {
        FragmentDisplay fragmentDisplay = new FragmentDisplay();
        Bundle bundle = new Bundle();
        bundle.putString("message", msg);
        fragmentDisplay.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragContainer, fragmentDisplay, null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}