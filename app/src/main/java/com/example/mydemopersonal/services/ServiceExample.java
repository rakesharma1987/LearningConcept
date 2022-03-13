package com.example.mydemopersonal.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class ServiceExample extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service:", "Classic Service created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service:", "Classic Service started");
        Log.d("Service:", Thread.currentThread().getName());

//        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Service:", "Classic Service stopped");
    }
}
