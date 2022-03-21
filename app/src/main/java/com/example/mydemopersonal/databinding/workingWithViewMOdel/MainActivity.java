package com.example.mydemopersonal.databinding.workingWithViewMOdel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityMain3Binding;

public class MainActivity extends AppCompatActivity {
    private ActivityMain3Binding binding;
    private MainActivityViewModel viewModel;
    private MainActivityViewModelFactory viewModelFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main3);
        viewModelFactory = new MainActivityViewModelFactory(new MutableLiveData<Integer>(125));
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setMyViewModel(viewModel);

    }
}