package com.example.mydemopersonal.locationConcept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityCurrentLocationBinding;
import com.example.mydemopersonal.utility.PermissionUtil;
import com.example.mydemopersonal.utility.Util;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CurrentLocationActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 999;
    private Geocoder geocoder;
    private List<Address> addresses;
    private ActivityCurrentLocationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_current_location);
        geocoder = new Geocoder(this, Locale.getDefault());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (PermissionUtil.isAcessFineLocationGranted(this)) {
            if (PermissionUtil.isLocationEnabled(this)) {
                setUpLocationListener();
            } else {
                PermissionUtil.showGPSNOtEnabled(this);
            }
        } else {
            PermissionUtil.requestAccessFineLocationPermission(this, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void setUpLocationListener() {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        LocationRequest request = new LocationRequest().setInterval(2000).setFastestInterval(2000).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
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
        client.requestLocationUpdates(request, new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for (Location location : locationResult.getLocations()){
                    double latutudde = location.getLatitude();
                    double longitude = location.getLongitude();
                    try {
                        addresses = geocoder.getFromLocation(latutudde, longitude, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d("Lat/Long : ", latutudde +", "+longitude);
                    String addLine = addresses.get(0).getAddressLine(0);
                    String adminArea = addresses.get(0).getAdminArea();
                    String countryCode = addresses.get(0).getCountryCode();
                    String countryName = addresses.get(0).getCountryName();
                    String featureName = addresses.get(0).getFeatureName();
                    String phone = addresses.get(0).getPhone();
                    String postalCode = addresses.get(0).getPostalCode();
                    String premises = addresses.get(0).getPremises();
                    String subAdminArea = addresses.get(0).getSubAdminArea();
                    String subLocality = addresses.get(0).getSubLocality();
                    String locality = addresses.get(0).getLocality();
                    binding.tvAddress.setText("Address : "+addLine);
                    binding.tvState.setText("State : "+adminArea);
                    binding.tvCountryCode.setText("Countyr Code : "+countryCode);
                    binding.tvCountryName.setText("Country Name : "+countryName);
                    binding.tvFeatureName.setText("Feature Name : "+featureName);
                    binding.tvPhone.setText("phone : "+phone);
                    binding.tvPostalCode.setText("Postal Code : "+postalCode);
                    binding.tvPremises.setText("Premisess : "+premises);
                    binding.tvDistrict.setText("District : "+subAdminArea);
                    binding.tvSubLocality.setText("Sub Locality : "+subLocality);
                    binding.tvLocality.setText("Locality : "+locality);

                    Util.showCustomToast(CurrentLocationActivity.this, "Lat : "+latutudde+" - "+"Lang : "+longitude);
                }
            }
        }, Looper.myLooper());
    }
}