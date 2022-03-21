package com.example.mydemopersonal.retrofitConcept.RetrofitWithMVVM.viewmodel;

import android.app.Application;
import android.app.ProgressDialog;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mydemopersonal.retrofitConcept.Posts;
import com.example.mydemopersonal.retrofitConcept.RetrofitWithMVVM.model.PostsRepository;

import java.util.List;

public class PostsViewModel extends AndroidViewModel {
    private PostsRepository postsRepository;

    public PostsViewModel(Application application){
        super(application);

        postsRepository = new PostsRepository(application);
    }

    public MutableLiveData<List<Posts>> getPostsData(){
        return postsRepository.getPostsData();
    }

    public MutableLiveData<Boolean> getIsLoading(){
        return postsRepository.getIsLoading();
    }
}
