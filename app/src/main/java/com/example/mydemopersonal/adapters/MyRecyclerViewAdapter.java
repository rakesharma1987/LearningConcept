package com.example.mydemopersonal.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.interfaces.OnItemClickListener;
import com.example.mydemopersonal.model.Persons;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    List<Persons> personsList;
    Context context;
    private OnItemClickListener listener;

    public MyRecyclerViewAdapter(List<Persons> personsList, Context context, OnItemClickListener listener) {
        this.personsList = personsList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_items, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Persons persons =personsList.get(position);
        String image = persons.getPhoto();
        byte[] imageAsBytes = Base64.decode(image.getBytes(), Base64.DEFAULT);

        holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        holder.tvName.setText(persons.getFirstName()+" "+persons.getLastName());
        holder.tvPhone.setText(persons.getPhone());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) listener.onItemClick(personsList, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return personsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvName, tvPhone;
       public MyViewHolder(@NonNull View itemView) {
           super(itemView);
           imageView = itemView.findViewById(R.id.imageView);
           tvName = itemView.findViewById(R.id.tvName);
           tvPhone = itemView.findViewById(R.id.tvPhone);
       }
   }
}
