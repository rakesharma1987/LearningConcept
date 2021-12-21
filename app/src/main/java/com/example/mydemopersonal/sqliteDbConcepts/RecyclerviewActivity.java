package com.example.mydemopersonal.sqliteDbConcepts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.adapters.MyRecyclerViewAdapter;
import com.example.mydemopersonal.db.DBAdapter;
import com.example.mydemopersonal.interfaces.OnItemClickListener;
import com.example.mydemopersonal.model.Persons;
import com.example.mydemopersonal.utility.Util;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Cursor cursor;
    private DBAdapter dbAdapter;
    private Context context;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        context = this;
        dbAdapter = new DBAdapter(context);
        dbAdapter.openDatabase();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        loadDataToRecyclerView();

    }

    private List<Persons> getAllPersonData(){
        cursor = dbAdapter.getAllData();
        List<Persons> data = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                Persons personalData = new Persons();
                personalData.setFirstName(cursor.getString(1)); // firstName
                personalData.setLastName(cursor.getString(2)); // lastName
                personalData.setEmail(cursor.getString(3)); // email
                personalData.setPhone(cursor.getString(4)); // phone
                personalData.setGender(cursor.getString(5)); // gender
                personalData.setPhoto(cursor.getString(6)); // photo
                data.add(personalData);
            }while (cursor.moveToNext());
        }
        return data;
    }

    private void loadDataToRecyclerView(){
        if (getAllPersonData().size() > 0){
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(getAllPersonData(), context, new OnItemClickListener() {
                @Override
                public void onItemClick(List<Persons> personsList, int position) {
                    Util.showCustomToast(context, personsList.get(position).getFirstName()+" "+personsList.get(position).getLastName());
                }
            });

            recyclerView.setAdapter(myRecyclerViewAdapter);
            myRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}