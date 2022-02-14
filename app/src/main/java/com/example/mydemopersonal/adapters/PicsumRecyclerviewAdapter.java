package com.example.mydemopersonal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mydemopersonal.R;
import com.example.mydemopersonal.paginationConcept.PicsumListData;

import java.util.List;

public class PicsumRecyclerviewAdapter extends RecyclerView.Adapter<PicsumRecyclerviewAdapter.MyViewHolder> {

    private Context context;
    private List<PicsumListData> listData;

    public PicsumRecyclerviewAdapter(Context context, List<PicsumListData> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.picsum_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PicsumListData data = listData.get(position);
        Glide.with(context).load(data.getDownloadUrl()).into(holder.imageView);
        holder.tvName.setText(data.getAuthorName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
