package com.example.mydemopersonal.databinding.viewModelWithDatabinding;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CounterActivityViewModelFactory implements ViewModelProvider.Factory {
    private MutableLiveData<Integer> count;
    CounterActivityViewModelFactory(MutableLiveData<Integer> count){
        this.count = count;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        if (aClass.isAssignableFrom(CounterActivityViewModel.class)){
            return (T) new CounterActivityViewModel(count);
        }
        throw new IllegalArgumentException("Unkonown view model class");
    }
}
