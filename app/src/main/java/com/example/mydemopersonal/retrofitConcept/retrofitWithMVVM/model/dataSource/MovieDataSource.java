package com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.dataSource;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.pojo.Movie;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.response.MovieDBResponse;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.services.ApiEndPoints;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.services.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {
    private ApiEndPoints apiEndPoints;
    private Application application;

    public MovieDataSource(ApiEndPoints apiEndPoints, Application application) {
        this.apiEndPoints = apiEndPoints;
        this.application = application;
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, Movie> loadCallback) {
        apiEndPoints = RetrofitInstance.getService(ApiEndPoints.class);
        Call<MovieDBResponse> call = apiEndPoints.getPopularMoviesWithPagging(application.getString(R.string.api_key), 1);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                Log.d("MyTag:", ""+movieDBResponse);
                ArrayList<Movie> movies = new ArrayList<>();
                movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                loadCallback.onResult(movies, loadParams.key+1);
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, Movie> loadCallback) {

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> loadInitialParams, @NonNull LoadInitialCallback<Integer, Movie> loadInitialCallback) {
        apiEndPoints = RetrofitInstance.getService(ApiEndPoints.class);
        Call<MovieDBResponse> call = apiEndPoints.getPopularMoviesWithPagging(application.getString(R.string.api_key), 1);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                Log.d("MyTag:", ""+movieDBResponse);
                ArrayList<Movie> movies = new ArrayList<>();
                movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                loadInitialCallback.onResult(movies, null, 2);
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
    }
}
