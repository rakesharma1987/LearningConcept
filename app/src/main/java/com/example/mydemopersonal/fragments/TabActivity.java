package com.example.mydemopersonal.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.adapters.MyViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private int[] tabIcon = {R.drawable.ic_alarm, R.drawable.ic_alarm, R.drawable.ic_alarm};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        tabLayout = findViewById(R.id.tabLayout);
        toolbar = findViewById(R.id.toolBar);
        viewPager = findViewById(R.id.viewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setUpTabIcon();

    }

    private void setUpTabIcon(){
        tabLayout.getTabAt(0).setIcon(tabIcon[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcon[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcon[2]);
    }

    private void setUpViewPager(ViewPager viewPager){
        MyViewPagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        FragmentCall fragmentCall = new FragmentCall();
        FragmentChat fragmentChat = new FragmentChat();

        pagerAdapter.addFragement(fragmentCall, "Call");
        pagerAdapter.addFragement(fragmentChat, "Chat");

        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return false;
    }
}