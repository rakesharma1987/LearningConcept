package com.example.mydemopersonal.databinding.viewModelWithDatabinding;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterActivityViewModel extends ViewModel {
    public MutableLiveData<Integer> count = new MutableLiveData<>();

    public CounterActivityViewModel(MutableLiveData<Integer> countMutableLiveData){
        count.postValue(countMutableLiveData.getValue());
    }

    public void getUpdatedCount(){
        count.postValue(count.getValue() + 1);
    }
}
