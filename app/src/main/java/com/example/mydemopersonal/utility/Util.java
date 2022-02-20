package com.example.mydemopersonal.utility;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mydemopersonal.R;
import com.google.android.material.snackbar.Snackbar;

public class Util {
    public static void showCustomToast(Context context, String message) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_layout, (ViewGroup) ((Activity) context).findViewById(R.id.mylayout));
        TextView msgTv = (TextView) layout.findViewById(R.id.textView1);
        msgTv.setText(message);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static void toast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showSnackBar(View view, String msg){
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void requestAccessFineLocationPermission(AppCompatActivity activity, int requestId){
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                requestId);

    }

    public static boolean isAcessFineLocationGranted(Context context){
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isLocationEnabled(Context context){
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public static void showGPSNOtEnabled(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name);
        builder.setMessage("GPS not enabled");
        builder.setCancelable(false);
        builder.setPositiveButton("Enabled", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        builder.show();
    }
}
