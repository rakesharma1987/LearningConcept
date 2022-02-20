package com.example.mydemopersonal.locationConcept;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.mydemopersonal.R;
import com.example.mydemopersonal.utility.Util;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;

public class CurrenctLocationActivity extends AppCompatActivity {
    private Context context;
    private int LOCATION_REQUEST_CODE = 101;
    double latitude, longitude;
    private Geocoder geocoder;
    private List<Address> addressList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currenct_location);
        context = this;
        geocoder = new Geocoder(context);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.isAcessFineLocationGranted(context)) {
            if (Util.isLocationEnabled(context)) {
                // location related task
                setUpCurrentLocation();
            } else {
                Util.showGPSNOtEnabled(context);
            }
        } else {
            Util.requestAccessFineLocationPermission(CurrenctLocationActivity.this, LOCATION_REQUEST_CODE);
        }
    }

    private void setUpCurrentLocation() {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(@NonNull Location location) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    Log.d("LatLang", latitude+" "+longitude);
                try {
                    addressList = geocoder.getFromLocation(latitude, longitude, 4);
                    Log.d("ADD", ""+addressList);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}