package com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityPostsBinding;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.adapter.PostDataAdapter;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.pojo.PostData;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.viewmodel.PostsViewModel;

import java.util.List;

public class PostsActivity extends AppCompatActivity {
    private PostsViewModel viewModel;
    private ProgressDialog progressDialog;
    private ActivityPostsBinding binding;
    private PostDataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_posts);
        viewModel = new ViewModelProvider(this).get(PostsViewModel.class);

        binding.setLifecycleOwner(this);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        viewModel.getPostsData().observe(this, new Observer<List<PostData>>() {
            @Override
            public void onChanged(List<PostData> posts) {
                Log.d("POSTS_DATA", posts.get(0).getTitle());
                adapter = new PostDataAdapter(PostsActivity.this, posts);
                binding.recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}