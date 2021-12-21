package com.example.mydemopersonal.db;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    public static MySharedPreference preference;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String FNAME = "_fname";
    private String LNAME = "_lname";

    public MySharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("_demo_", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static MySharedPreference getInstance(Context context){
        if (preference == null){
            preference = new MySharedPreference(context);
        }
        return preference;
    }


    public void setFirstName(String fName){
        editor.putString(FNAME, fName);
        editor.apply();
    }

    public String getFirstName(){
        return sharedPreferences.getString(FNAME, "");
    }

    public void setLasttName(String lName){
        editor.putString(LNAME, lName);
        editor.apply();
    }

    public String getLastName(){
        return sharedPreferences.getString(LNAME, "");
    }

}
