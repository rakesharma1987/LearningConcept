package com.example.mydemopersonal.databinding.viewModelWithDatabinding;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TwowayViewmodel extends ViewModel {
    public MutableLiveData<String> name = new MutableLiveData<>();

    public TwowayViewmodel() {
        name.postValue("Rakesh");
    }
}
