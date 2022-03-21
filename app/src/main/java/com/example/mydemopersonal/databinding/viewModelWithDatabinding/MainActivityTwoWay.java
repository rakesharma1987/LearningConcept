package com.example.mydemopersonal.databinding.viewModelWithDatabinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityMainTwoWayBinding;

public class MainActivityTwoWay extends AppCompatActivity {
    private ActivityMainTwoWayBinding binding;
    private TwowayViewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_two_way);
        viewmodel = new ViewModelProvider(this).get(TwowayViewmodel.class);
        binding.setTwoWayViewmodel(viewmodel);
        binding.setLifecycleOwner(this);
    }
}