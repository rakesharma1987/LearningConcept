package com.example.mydemopersonal.retrofitConcept.RetrofitWithMVVM.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.mydemopersonal.retrofitConcept.PostrecyclerViewActivity;
import com.example.mydemopersonal.retrofitConcept.Posts;
import com.example.mydemopersonal.retrofitConcept.RetrofitInstanceCreator;
import com.example.mydemopersonal.retrofitConcept.RetrofitWithMVVM.services.ApiEndPoints;
import com.example.mydemopersonal.retrofitConcept.RetrofitWithMVVM.services.RetrofitInstance;
import com.example.mydemopersonal.retrofitConcept.ServiceEndPoints;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsRepository {
    private Application application;
    public MutableLiveData<List<Posts>> postsMutableData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public PostsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Posts>> getPostsData(){
        isLoading.postValue(true);
        ApiEndPoints service = RetrofitInstance.getService(ApiEndPoints.class);
        Call<List<Posts>> call = service.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                isLoading.postValue(false);
                List<Posts> posts = response.body();
                postsMutableData.postValue(posts);
                for (Posts posts1 : posts){
                    Log.d("POST_DATA",posts1.getUserId()+"\n"+posts1.getId()+"\n"+posts1.getTitle()+"\n"+posts1.getBody());
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                  isLoading.postValue(false);
//                Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
            return postsMutableData;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}
