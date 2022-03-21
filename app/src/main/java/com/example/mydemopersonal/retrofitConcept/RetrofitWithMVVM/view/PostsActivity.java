package com.example.mydemopersonal.retrofitConcept.RetrofitWithMVVM.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.retrofitConcept.Posts;
import com.example.mydemopersonal.retrofitConcept.RetrofitWithMVVM.viewmodel.PostsViewModel;

import java.util.List;

public class PostsActivity extends AppCompatActivity {
    private PostsViewModel viewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        viewModel = new ViewModelProvider(this).get(PostsViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    progressDialog.show();
                }else {
                    progressDialog.dismiss();
                }
            }
        });

        viewModel.getPostsData().observe(this, new Observer<List<Posts>>() {
            @Override
            public void onChanged(List<Posts> posts) {
                Log.d("POSTS_DATA", posts.get(0).getTitle());
            }
        });
    }
}