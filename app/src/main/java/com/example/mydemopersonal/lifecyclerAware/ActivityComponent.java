package com.example.mydemopersonal.lifecyclerAware;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class ActivityComponent implements DefaultLifecycleObserver {
    private final String activityClassName;
    private String TAG = "lifecycle";

    public ActivityComponent(String activityClassName) {
        this.activityClassName = activityClassName;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onCreate(owner);
        Log.d(TAG, "ActivityComponent invoked onCreate() for "+activityClassName);
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStart(owner);
        Log.d(TAG, "ActivityComponent invoked onStart() for "+activityClassName);
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onResume(owner);
        Log.d(TAG, "ActivityComponent invoked onResume() for "+activityClassName);
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onPause(owner);
        Log.d(TAG, "ActivityComponent invoked onPause() for "+activityClassName);
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStop(owner);
        Log.d(TAG, "ActivityComponent invoked onStop() for "+activityClassName);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onDestroy(owner);
        Log.d(TAG, "ActivityComponent invoked onDestroy() for "+activityClassName);
    }

}
