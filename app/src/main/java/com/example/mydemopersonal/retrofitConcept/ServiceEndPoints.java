package com.example.mydemopersonal.retrofitConcept;

import com.example.mydemopersonal.paginationConcept.PicsumListData;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceEndPoints {
    @GET("/posts")
    Call<List<Posts>> getPosts();

    @GET("v2/list")
    Call<List<PicsumListData>> getPicSumData(@Query("page") int page, @Query("limit") int limit);

}
