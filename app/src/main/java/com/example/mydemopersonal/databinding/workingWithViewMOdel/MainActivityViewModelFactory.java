package com.example.mydemopersonal.databinding.workingWithViewMOdel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {
    MutableLiveData<Integer> startingTotal;
    public MainActivityViewModelFactory(MutableLiveData<Integer> startingTotal){
        this.startingTotal = startingTotal;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        if (aClass.isAssignableFrom(MainActivityViewModel.class)){
            return (T) new MainActivityViewModel(startingTotal);
        }
        throw new IllegalArgumentException("Unknown view model class");
    }
}
