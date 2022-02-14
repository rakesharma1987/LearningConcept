package com.example.mydemopersonal.retrofitConcept;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mydemopersonal.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostrecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postrecycler_view);
        callPostsService();
    }

    private void callPostsService(){
        ProgressDialog progressDialog = new ProgressDialog(PostrecyclerViewActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ServiceEndPoints service = RetrofitInstanceCreator.getInstance(ServiceEndPoints.class);
        if (service == null) return;
        Call<List<Posts>> call = service.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                progressDialog.dismiss();
                List<Posts> posts = response.body();
                for (Posts posts1 : posts){
                    Log.d("POST_DATA",posts1.getUserId()+"\n"+posts1.getId()+"\n"+posts1.getTitle()+"\n"+posts1.getBody());
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PostrecyclerViewActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}