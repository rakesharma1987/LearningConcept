package com.example.mydemopersonal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.db.MySharedPreference;
import com.example.mydemopersonal.model.PersonalData;
import com.example.mydemopersonal.model.PersonalParcebleData;
import com.google.gson.Gson;

public class SecondActivity extends AppCompatActivity {

    private PersonalData personalData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        String intentData = getIntent().getStringExtra("personalData");
//        personalData = new Gson().fromJson(intentData, PersonalData.class);
//        Toast.makeText(this, personalData.getfName()+" "+personalData.getsName(), Toast.LENGTH_SHORT).show();

//        Bundle bundle = getIntent().getExtras();
//        Toast.makeText(this, bundle.getString("fname")+" "+bundle.getString("lname"), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, MySharedPreference.getInstance(this).getFirstName() +" "+MySharedPreference.getInstance(this).getLastName(), Toast.LENGTH_SHORT).show();

        // TODO : Receive parceble data from Intent
//        Intent intent = getIntent();
//        PersonalParcebleData data = intent.getParcelableExtra("parcebleData");
//        Toast.makeText(SecondActivity.this, data.getfName(), Toast.LENGTH_SHORT).show();

        // TODO : Receive seriazable data from Intent
        Intent intent = getIntent();
        PersonalData data = (PersonalData) intent.getSerializableExtra("seriazableData");
        Toast.makeText(SecondActivity.this, data.getfName()+" "+data.getsName(), Toast.LENGTH_SHORT).show();
    }
}