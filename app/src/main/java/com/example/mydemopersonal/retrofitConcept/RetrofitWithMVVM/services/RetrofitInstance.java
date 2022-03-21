package com.example.mydemopersonal.retrofitConcept.RetrofitWithMVVM.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;
//    private static String BASE_URL="https://api.themoviedb.org/3/";
    private static String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static <S> S getService(Class<S> serviceClassName){


        if(retrofit==null){

            retrofit=new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(serviceClassName);
    }

}
