package com.example.mydemopersonal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.PersonalDetailItemBinding;
import com.example.mydemopersonal.model.PersonalData;

import java.util.ArrayList;
import java.util.List;

public class PersonalDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<PersonalData> personalData = new ArrayList<>();

    public PersonalDataAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<PersonalData> items){
        personalData.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PersonalDetailItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.personal_detail_item,parent, false);
        return new PersonalDetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PersonalData personalData = null;
        this.onBindViewHOlder(holder, position, personalData);
    }

    public void onBindViewHOlder(RecyclerView.ViewHolder holder, int position, PersonalData data){
        PersonalDetailViewHolder viewHolder = (PersonalDetailViewHolder) holder;
        PersonalData data1 = data==null? personalData.get(position) : data;
        ((PersonalDetailViewHolder) holder).bind(data1);

    }

    @Override
    public int getItemCount() {
        return personalData.size();
    }
}
