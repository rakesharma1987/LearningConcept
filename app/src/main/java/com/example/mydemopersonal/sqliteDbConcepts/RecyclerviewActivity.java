package com.example.mydemopersonal.sqliteDbConcepts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

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
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        context = this;
        dbAdapter = new DBAdapter(context);
        dbAdapter.openDatabase();
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progress_bar);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
//        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(gridLayoutManager);

//        loadDataToRecyclerView();
        new MyTask().execute();

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

    class MyTask extends AsyncTask<Void, Integer, List<Persons>>{
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Wait fetching data...");
            progressDialog.setCancelable(false);
            progressDialog.show();
//            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Persons> doInBackground(Void... voids) {
            cursor = dbAdapter.getAllData();

            progressDialog.setMax(cursor.getCount());
            List<Persons> data = new ArrayList<>();
            int i = 0;
            if (cursor.moveToFirst()){
                do {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Persons personalData = new Persons();
                    personalData.setFirstName(cursor.getString(1)); // firstName
                    personalData.setLastName(cursor.getString(2)); // lastName
                    personalData.setEmail(cursor.getString(3)); // email
                    personalData.setPhone(cursor.getString(4)); // phone
                    personalData.setGender(cursor.getString(5)); // gender
                    personalData.setPhoto(cursor.getString(6)); // photo
                    data.add(personalData);
                    publishProgress((int) ((i / cursor.getCount())));
                }while (cursor.moveToNext());
            }
            return data;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
//            progressDialog.setProgress(values[0]);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(List<Persons> personsList) {
            progressDialog.dismiss();
//            progressBar.setVisibility(View.GONE);
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(personsList, context, new OnItemClickListener() {
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