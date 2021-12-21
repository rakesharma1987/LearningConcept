package com.example.mydemopersonal.roomDb;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceHelper {
    private static  SharedPreferenceHelper insance;
    private static final String PREF_TIME = "pref_time";
    private SharedPreferences preferences;

    private SharedPreferenceHelper(Context context){
       preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferenceHelper getInstance(Context context){
        if (insance == null){
            insance = new SharedPreferenceHelper(context);
        }
        return insance;
    }

    public void saveUpdateTime(long time){
        preferences.edit().putLong(PREF_TIME, time).apply();
    }

    public long getUpdateTime(){
        return preferences.getLong(PREF_TIME, 0);
    }

}
