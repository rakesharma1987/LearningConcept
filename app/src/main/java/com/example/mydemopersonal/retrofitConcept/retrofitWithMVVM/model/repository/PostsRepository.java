package com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.pojo.PostData;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.services.ApiEndPoints;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.services.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsRepository {
    private Application application;
    public MutableLiveData<List<PostData>> postsMutableData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public PostsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<PostData>> getPostsData(){
        isLoading.postValue(true);
        ApiEndPoints service = RetrofitInstance.getService(ApiEndPoints.class);
        Call<List<PostData>> call = service.getPosts();
        call.enqueue(new Callback<List<PostData>>() {
            @Override
            public void onResponse(Call<List<PostData>> call, Response<List<PostData>> response) {
                isLoading.postValue(false);
                List<PostData> posts = response.body();
                postsMutableData.postValue(posts);
                for (PostData posts1 : posts){
                    Log.d("POST_DATA",posts1.getUserId()+"\n"+posts1.getId()+"\n"+posts1.getTitle()+"\n"+posts1.getBody());
                }
            }

            @Override
            public void onFailure(Call<List<PostData>> call, Throwable t) {
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
