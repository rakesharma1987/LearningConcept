package com.example.mydemopersonal.retrofitConcept.RetrofitWithMVVM.services;

import com.example.mydemopersonal.paginationConcept.PicsumListData;
import com.example.mydemopersonal.retrofitConcept.Posts;
import com.example.mydemopersonal.retrofitConcept.RetrofitWithMVVM.model.MovieDBResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoints {
    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("/posts")
    Call<List<Posts>> getPosts();

    @GET("v2/list")
    Call<List<PicsumListData>> getPicSumData(@Query("page") int page, @Query("limit") int limit);
}
