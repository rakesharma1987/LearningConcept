package com.example.mydemopersonal.firebaseConcept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.adapters.PersonalDataAdapter;
import com.example.mydemopersonal.databinding.ActivityListBinding;
import com.example.mydemopersonal.model.PersonalData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private PersonalDataAdapter personalDataAdapter;
    private ActivityListBinding binding;
    private PersonalDataDao dao;
    private String key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        binding.rvData.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvData.setLayoutManager(linearLayoutManager);
        personalDataAdapter = new PersonalDataAdapter(this);
        binding.rvData.setAdapter(personalDataAdapter);
        dao = new PersonalDataDao();
        loadData();



    }

    private void loadData() {
        dao.get().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<PersonalData> list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PersonalData personalData = dataSnapshot.getValue(PersonalData.class);
                    personalData.setKey(dataSnapshot.getKey());
                    list.add(personalData);
                    key = dataSnapshot.getKey();
                }
                personalDataAdapter.setItems(list);
                personalDataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("tag", error.getDetails());
            }
        });
    }
}