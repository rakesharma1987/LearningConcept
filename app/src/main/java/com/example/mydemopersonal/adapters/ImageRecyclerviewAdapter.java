package com.example.mydemopersonal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.LayoutImagesBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageRecyclerviewAdapter extends RecyclerView.Adapter<ImageRecyclerviewAdapter.MyViewHolder> {
    private Context context;
    private List<String> imageList;

    public ImageRecyclerviewAdapter(Context context, List<String> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_images, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(imageList.get(position)).into(holder.binding.image);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        LayoutImagesBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = LayoutImagesBinding.bind(itemView);
        }
    }
}
