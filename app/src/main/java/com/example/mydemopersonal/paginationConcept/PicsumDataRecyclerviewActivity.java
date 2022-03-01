package com.example.mydemopersonal.paginationConcept;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.adapters.PicsumRecyclerviewAdapter;
import com.example.mydemopersonal.databinding.ActivityPicsumDataRecyclerviewBinding;
import com.example.mydemopersonal.retrofitConcept.RetrofitInstanceCreator;
import com.example.mydemopersonal.retrofitConcept.ServiceEndPoints;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PicsumDataRecyclerviewActivity extends AppCompatActivity {
    private int page = 0, limit = 10;
    private ActivityPicsumDataRecyclerviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPicsumDataRecyclerviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getPicSumDataServiceCall();

        binding.nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                    page++;
                    binding.progressBar.setVisibility(View.VISIBLE);
                    getPicSumDataServiceCall();
                }
            }
        });


    }

    private void getPicSumDataServiceCall() {
        ServiceEndPoints service = RetrofitInstanceCreator.getInstance(ServiceEndPoints.class);
        if (service == null) return;
        Call<List<PicsumListData>> call = service.getPicSumData(page, limit);
        call.enqueue(new Callback<List<PicsumListData>>() {
            @Override
            public void onResponse(Call<List<PicsumListData>> call, Response<List<PicsumListData>> response) {
                binding.progressBar.setVisibility(View.GONE);
                List<PicsumListData> data = response.body();
                PicsumRecyclerviewAdapter picsumRecyclerviewAdapter = new PicsumRecyclerviewAdapter(PicsumDataRecyclerviewActivity.this, data);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(PicsumDataRecyclerviewActivity.this));
                binding.recyclerView.setAdapter(picsumRecyclerviewAdapter);
                picsumRecyclerviewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<PicsumListData>> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(PicsumDataRecyclerviewActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}