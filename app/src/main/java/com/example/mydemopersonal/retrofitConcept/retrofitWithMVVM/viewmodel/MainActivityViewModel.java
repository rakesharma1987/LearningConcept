package com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.pojo.Movie;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.repository.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository=new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAllMovies(){

        return movieRepository.getMutableLiveData();
    }


}
