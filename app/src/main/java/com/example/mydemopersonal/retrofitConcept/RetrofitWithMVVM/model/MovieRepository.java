package com.example.mydemopersonal.retrofitConcept.RetrofitWithMVVM.model;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.retrofitConcept.RetrofitWithMVVM.services.ApiEndPoints;
import com.example.mydemopersonal.retrofitConcept.RetrofitWithMVVM.services.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ArrayList<Movie> movies=new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData=new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {

        ApiEndPoints movieDataService = RetrofitInstance.getService(ApiEndPoints.class);
        Call<MovieDBResponse> call = movieDataService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                if (movieDBResponse != null && movieDBResponse.getMovies() != null) {
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
