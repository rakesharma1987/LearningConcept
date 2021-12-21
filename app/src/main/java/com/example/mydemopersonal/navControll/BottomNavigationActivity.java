package com.example.mydemopersonal.navControll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.fragments.Fragment1;
import com.example.mydemopersonal.fragments.Fragment2;
import com.example.mydemopersonal.fragments.Fragment3;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        navigationView = findViewById(R.id.bottomNavigationVIew);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.fragment1);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.fragment1:
                onNavigationviewItemClick(new Fragment1());
                break;
            case R.id.fragment2:
                onNavigationviewItemClick(new Fragment2());
                break;
            case R.id.fragment3:
                onNavigationviewItemClick(new Fragment3());
                break;
        }
        return true;
    }

    private void onNavigationviewItemClick(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}