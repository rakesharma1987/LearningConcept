package com.example.mydemopersonal.sqliteDbConcepts;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.ActionOnlyNavDirections;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ListviewPersonDataBinding;
import com.example.mydemopersonal.databinding.PersonalDetailItemBinding;
import com.example.mydemopersonal.interfaces.ListviewListener;
import com.example.mydemopersonal.interfaces.OnItemClickListener;
import com.example.mydemopersonal.model.PersonalData;
import com.example.mydemopersonal.model.Persons;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {
    private List<Persons> personalData;
    private Activity activity;
    private LayoutInflater inflater;
    private ListviewListener listviewListener;
    private OnItemClickListener listener;

    public ListAdapter(Activity activity, List<Persons> personalData, OnItemClickListener listener) {
        this.personalData = personalData;
        this.activity = activity;
        this.listener = listener;
    }

    public ListAdapter(Activity activity, List<Persons> personalData) {
        this.personalData = personalData;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return personalData.size();
    }

    @Override
    public Persons getItem(int position) {
        return personalData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //TODO : Below will be used when used databinding in XML
        /*ListviewPersonDataBinding binding;
        if (convertView == null){
            convertView = LayoutInflater.from(activity).inflate(R.layout.listview_person_data, null);
            binding = DataBindingUtil.bind(convertView);
            convertView.setTag(binding);
        }else {
            binding = (ListviewPersonDataBinding) convertView.getTag();
        }
        binding.setPersons(personalData.get(position));
        return binding.getRoot();*/

        convertView = LayoutInflater.from(activity).inflate(R.layout.listview_person_data_without_databinding, parent, false);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView firstName = convertView.findViewById(R.id.tvFirstName);
        TextView lastName = convertView.findViewById(R.id.tvLastName);
        TextView email = convertView.findViewById(R.id.tvEmail);
        TextView phone = convertView.findViewById(R.id.tvPhone);
        TextView gender = convertView.findViewById(R.id.tvGender);

        String image = personalData.get(position).getPhoto();
        byte[] imageAsBytes = Base64.decode(image.getBytes(), Base64.DEFAULT);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        firstName.setText(personalData.get(position).getFirstName());
        lastName.setText(personalData.get(position).getLastName());
        email.setText(personalData.get(position).getEmail());
        phone.setText(personalData.get(position).getPhone());
        gender.setText(personalData.get(position).getGender());

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (listener != null) listener.onItemClick(personalData, position);
//            }
//        });
        return convertView;
    }

}
