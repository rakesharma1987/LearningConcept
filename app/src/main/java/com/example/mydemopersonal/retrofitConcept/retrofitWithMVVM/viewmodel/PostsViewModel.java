package com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.pojo.PostData;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.repository.PostsRepository;

import java.util.List;

public class PostsViewModel extends AndroidViewModel {
    private PostsRepository postsRepository;

    public PostsViewModel(Application application){
        super(application);

        postsRepository = new PostsRepository(application);
    }

    public MutableLiveData<List<PostData>> getPostsData(){
        return postsRepository.getPostsData();
    }

    public MutableLiveData<Boolean> getIsLoading(){
        return postsRepository.getIsLoading();
    }
}
