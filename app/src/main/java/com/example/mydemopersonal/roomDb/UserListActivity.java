package com.example.mydemopersonal.roomDb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.adapters.ListViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    UserDao userDao;
    ListView listView;
    List<User> list;
    ListViewAdapter listViewAdapter;
    User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        userDao = AppDataBase.getInstance(this).userDao();
        listView = findViewById(R.id.listUser);
        new FetchUserTask().execute();
    }

    private class FetchUserTask extends AsyncTask<Void, Void, List<User>>{
        AlertDialog.Builder builder = new AlertDialog.Builder(UserListActivity.this);
        AlertDialog dialog = builder.create();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            builder.setMessage("Loading...pls wait");
            dialog.show();
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            List<User> list = userDao.getAllUser();
            return list;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            dialog.dismiss();
            list = users;
            listViewAdapter = new ListViewAdapter(list, UserListActivity.this);
            listView.setAdapter(listViewAdapter);
        }
    }


}