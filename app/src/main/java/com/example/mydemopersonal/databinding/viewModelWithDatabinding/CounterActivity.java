package com.example.mydemopersonal.databinding.viewModelWithDatabinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityCounterBinding;

public class CounterActivity extends AppCompatActivity {
    public ActivityCounterBinding binding;
    public CounterActivityViewModel viewModel;
    public CounterActivityViewModelFactory viewModelProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter);
        viewModelProvider = new CounterActivityViewModelFactory(new MutableLiveData<>(1));
        viewModel = new ViewModelProvider(this, viewModelProvider).get(CounterActivityViewModel.class);
//        binding.setCounterViewModel(viewModel);

        viewModel.count.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvCount.setText(String.valueOf(viewModel.count));
            }
        });
    }
}