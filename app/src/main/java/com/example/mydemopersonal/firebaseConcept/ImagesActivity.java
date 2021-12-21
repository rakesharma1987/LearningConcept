package com.example.mydemopersonal.firebaseConcept;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.adapters.ImageRecyclerviewAdapter;
import com.example.mydemopersonal.databinding.ActivityImagesBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity {
    private ActivityImagesBinding binding;
    private StorageReference storageReference;
    private List<String> imagedsURlList = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    private ImageRecyclerviewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.progressBar.setVisibility(View.VISIBLE);
        gridLayoutManager = new GridLayoutManager(this, 2);
        binding.rvImage.setLayoutManager(gridLayoutManager);
        adapter = new ImageRecyclerviewAdapter(this, imagedsURlList);
        storageReference = FirebaseStorage.getInstance().getReference().child("images");
        storageReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                    for(StorageReference reference : listResult.getItems()){
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imagedsURlList.add(uri.toString());
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                binding.rvImage.setAdapter(adapter);
                                binding.progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
            }
        });


    }
}