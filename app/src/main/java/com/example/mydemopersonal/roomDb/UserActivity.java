package com.example.mydemopersonal.roomDb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.AsyncListUtil;
import androidx.room.Room;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityUserBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private ActivityUserBinding binding;
    AppDataBase appDataBase;
    private User user;
    private int userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user);
//        appDataBase = Room.databaseBuilder(UserActivity.this, AppDataBase.class, "UserDb").build();
//        UserDao userDao = appDataBase.userDao();
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User(binding.etFname.getText().toString(), binding.etLname.getText().toString(), binding.etEmail.getText().toString(),
                        binding.etPhone.getText().toString());
                InsertUsertask insertUsertask = new InsertUsertask();
                insertUsertask.execute(Collections.singletonList(user));
            }
        });

        binding.btnAllUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserActivity.this, UserListActivity.class));
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(UserActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.id_layout);
                dialog.show();
                EditText etId = dialog.findViewById(R.id.etUserId);
                Button btnDelete = dialog.findViewById(R.id.btnDelete);
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userId = Integer.parseInt(etId.getText().toString());
                        user =  new User();
                        user.setuId(userId);
                        new DeleteUserSync().execute();
                        dialog.dismiss();
                    }
                });
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(UserActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.user_update_dialog);
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                EditText etId = dialog.findViewById(R.id.etId);
                EditText etFname = dialog.findViewById(R.id.etFname);
                EditText etLname = dialog.findViewById(R.id.etLname);
                EditText etEmail = dialog.findViewById(R.id.etEmail);
                EditText etPhone = dialog.findViewById(R.id.etPhone);
                Button btnUpdate = dialog.findViewById(R.id.btnUpdate1);

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        user = new User();
                        user.setuId(Integer.parseInt(etId.getText().toString()));
                        user.setfName(etFname.getText().toString());
                        user.setlName(etLname.getText().toString());
                        user.setPhoneNo(etPhone.getText().toString());
                        user.setEmail(etEmail.getText().toString());
                        new UpdateUserTask().execute();
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private class InsertUsertask extends AsyncTask<List<User>, Void, List<User>>{

        @Override
        protected List<User> doInBackground(List<User>... lists) {
            List<User> list = lists[0];
            UserDao userDao = AppDataBase.getInstance(getApplicationContext()).userDao();
            ArrayList<User> newList = new ArrayList<>(list);
            List<Long> result = userDao.insertUser(newList.toArray(new User[0]));
            return list;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            Toast.makeText(getApplicationContext(), users.size()+" row inserted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public class DeleteUserSync extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... string) {
            AppDataBase.getInstance(UserActivity.this).userDao().deleteByUserId(user);
            return " delted successfully";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(UserActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }

    public class UpdateUserTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... string) {
            AppDataBase.getInstance(UserActivity.this).userDao().updateUser(user);
            return " Updated successfully";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(UserActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }
}