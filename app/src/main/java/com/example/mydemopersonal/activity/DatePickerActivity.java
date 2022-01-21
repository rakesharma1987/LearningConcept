package com.example.mydemopersonal.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.databinding.adapters.AdapterViewBindingAdapter;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityDatePickerBinding;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity {
    ActivityDatePickerBinding binding;
    Calendar calendar;
    int DATE_OF_MONTH, MONTH, YEAR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDatePickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        calendar = Calendar.getInstance();
        DATE_OF_MONTH = calendar.get(Calendar.DAY_OF_MONTH);
        MONTH = calendar.get(Calendar.MONTH);
        YEAR = calendar.get(Calendar.YEAR);

        Log.d("DATE:", DATE_OF_MONTH+"/"+(MONTH + 1)+"/"+YEAR);
        binding.btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // it is material design
//                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
//                builder.setTitleText("Select Date");
//                MaterialDatePicker<Long> picker = builder.build();
//                picker.show(getSupportFragmentManager(), picker.toString());

//                MaterialDatePicker.Builder<Pair<Long, Long>> builder =
//                        MaterialDatePicker.Builder.dateRangePicker();
//                CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
//                builder.setCalendarConstraints(constraintsBuilder.build());
//                MaterialDatePicker<?> picker = builder.build();
//                picker.show(getSupportFragmentManager(), picker.toString());

                DatePickerDialog datePickerDialog = new DatePickerDialog(DatePickerActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                    }
                }, YEAR, MONTH, DATE_OF_MONTH);
                datePickerDialog.show();
            }
        });
    }
}