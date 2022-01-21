package com.example.mydemopersonal.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityTimepickerBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.time.LocalTime;
import java.util.Calendar;

public class TimepickerActivity extends AppCompatActivity {
    ActivityTimepickerBinding binding;
    Calendar calendar;
    int hr, min, second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimepickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        calendar = Calendar.getInstance();
        hr = calendar.get(Calendar.HOUR);
        min = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);

        Log.d("TIME", hr+":"+min+":"+second);

        binding.btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
//                TimePickerDialog timePickerDialog = new TimePickerDialog(TimepickerActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
//
//                    }
//                }, hr, min, true);
//                timePickerDialog.show();

                new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(LocalTime.now().getHour())
                        .setMinute(LocalTime.now().getMinute())
                        .build()
                        .show(getSupportFragmentManager(), "Tie");

            }
        });


    }
}