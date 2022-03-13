package com.example.mydemopersonal.services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class JobIntentServiceExample extends JobIntentService {
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d("Service:", "JobIntent Service started.");
        Log.d("Service:", Thread.currentThread().getName());
        for (int i=0; i<=10; i++){
            Log.d("Service:", "I = "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        onStopCurrentWork();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Service:", "JobIntent Service stopped.");
    }

    public static void myBackgroundService(Context context, Intent intent){
        enqueueWork(context, JobIntentServiceExample.class, 1, intent);
    }
}
