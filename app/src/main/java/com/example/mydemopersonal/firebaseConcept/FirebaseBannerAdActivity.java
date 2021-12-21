package com.example.mydemopersonal.firebaseConcept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mydemopersonal.R;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

public class FirebaseBannerAdActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
//    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_banner_ad);
//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
    }
}