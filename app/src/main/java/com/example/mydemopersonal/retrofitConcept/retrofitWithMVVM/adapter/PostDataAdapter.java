package com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.LayoutPostDataItemsBinding;
import com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.pojo.PostData;

import java.util.List;

public class PostDataAdapter extends RecyclerView.Adapter<PostDataAdapter.MyViewHolder>{

    private Context context;
    private List<PostData> postDataList;

    public PostDataAdapter(Context context, List<PostData> postDataList) {
        this.context = context;
        this.postDataList = postDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutPostDataItemsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_post_data_items,parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PostData postData = postDataList.get(position);
        holder.binding.setPostData(postData);

    }

    @Override
    public int getItemCount() {
        return postDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private final LayoutPostDataItemsBinding binding;
        public MyViewHolder(LayoutPostDataItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
