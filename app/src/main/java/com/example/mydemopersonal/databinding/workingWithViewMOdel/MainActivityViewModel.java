package com.example.mydemopersonal.databinding.workingWithViewMOdel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    public MutableLiveData<Integer> count =new MutableLiveData<Integer>();
    public MainActivityViewModel(MutableLiveData<Integer> startingTotal){
        count.postValue(startingTotal.getValue());
    }
    public void setCurrentCount(){
        count.postValue(count.getValue() + 1);
    }
}
