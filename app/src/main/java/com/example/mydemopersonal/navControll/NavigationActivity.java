package com.example.mydemopersonal.navControll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.fragments.Fragment1;
import com.example.mydemopersonal.fragments.Fragment2;
import com.example.mydemopersonal.fragments.Fragment3;
import com.google.android.material.navigation.NavigationView;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavController navController;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

//        setTitle(toolbar, "Fragment 1");

        navController = Navigation.findNavController(this, R.id.fragment);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);

        NavigationUI.setupWithNavController(toolbar, navController);
        NavigationUI.setupActionBarWithNavController(NavigationActivity.this, navController, drawerLayout);


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open
                , R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerLayout);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerLayout);
            }
        };

        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerLayout.addDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.fragment1:
//                setTitle(toolbar, "Fragment 1");
                onNavigationviewItemClick(new Fragment1());
                break;
            case R.id.fragment2:
                setTitle(toolbar, "Fragment 2");
                onNavigationviewItemClick(new Fragment2());
                break;
            case R.id.fragment3:
                setTitle(toolbar, "Fragment 3");
                onNavigationviewItemClick(new Fragment3());
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onNavigationviewItemClick(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    private void setTitle(Toolbar toolbar, String title){
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
    }
}