package com.example.mydemopersonal.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.mydemopersonal.BR;
import com.example.mydemopersonal.databinding.PersonalDetailItemBinding;
import com.example.mydemopersonal.model.PersonalData;

import java.security.PublicKey;

public class PersonalDetailViewHolder extends RecyclerView.ViewHolder {
    private PersonalDetailItemBinding binding;

    public PersonalDetailViewHolder(PersonalDetailItemBinding personalDetailItemBinding){
        super(personalDetailItemBinding.getRoot());
        binding = personalDetailItemBinding;
    }

    public void bind(/*Object data*/PersonalData data){
//        binding.setVariable(BR._all, data);

        binding.tvName.setText("Name : "+data.getfName()+" "+data.getsName());
        binding.tvEmail.setText("Email : "+data.getEmail());
        binding.tvPhone.setText("Phone : "+data.getPhone());
        binding.tvGender.setText("Gender : "+data.getGender());

        binding.executePendingBindings();

    }
}
