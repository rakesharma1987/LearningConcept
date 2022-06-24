package com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.services;

import com.example.mydemopersonal.paginationConcept.PicsumListData;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.response.MovieDBResponse;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.pojo.PostData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoints {
    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("/posts")
    Call<List<PostData>> getPosts();

    @GET("v2/list")
    Call<List<PicsumListData>> getPicSumData(@Query("page") int page, @Query("limit") int limit);

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMoviesWithPagging(@Query("api_key") String apiKey, @Query("page") int page);
}
