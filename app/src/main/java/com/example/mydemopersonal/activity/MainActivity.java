package com.example.mydemopersonal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.db.MySharedPreference;
import com.example.mydemopersonal.model.PersonalParcebleData;
import com.example.mydemopersonal.utility.Util;
import com.example.mydemopersonal.databinding.ActivityMainBinding;
import com.example.mydemopersonal.model.PersonalData;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Context context;
    private PersonalData personalData;
    private PopupMenu popupMenu;
    private MySharedPreference  preference;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        personalData = new PersonalData();
        preference = new MySharedPreference(context);

        //TODO : initialising FirebaseDatabase , DatabseReference
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user");

        //TODO : Setting default data
        binding.etFname.setText("Rakesh");
        binding.etLname.setText("Kumar");
        binding.etEmail.setText("kumar@gmail.com");
        binding.etPhone.setText("9625033237");

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, SecondActivity.class);
                /*  TODO : Taking data in Object */
//                personalData.setfName(binding.etFname.getText().toString());
//                personalData.setsName(binding.etLname.getText().toString());
//                intent.putExtra("personalData", new Gson().toJson(personalData));

                /* TODO : Creating bundle and dtaking data in bundle */
//                Bundle bundle = new Bundle();
//                bundle.putString("fname", binding.etFname.getText().toString());
//                bundle.putString("lname", binding.etLname.getText().toString());
//                intent.putExtras(bundle);
//                startActivity(intent);

                // TODO : Calling of Context menu
//                registerForContextMenu(binding.btnSubmit);


                // TODO : Creating popup menu
                /*popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater().inflate(R.menu.option_menu, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.alarm:
//                                Toast.makeText(context, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                                Util.showCustomToast(context, menuItem.getTitle().toString());
                                break;

                            case R.id.map:
                                Snackbar.make(findViewById(android.R.id.content), menuItem.getTitle(), Snackbar.LENGTH_LONG).show();
                                break;
                        }
                        return true;
                    }
                });*/

                // TODO : Saving data into Shredpreference

                /*preference.setFirstName(binding.etFname.getText().toString());
                preference.setLasttName(binding.etLname.getText().toString());
                Intent intent = new Intent(context, SecondActivity.class);
                startActivity(intent);*/

                //TODO : insert data into firebase databse
//                RadioButton radioButton = (RadioButton) findViewById(binding.rgGender.getCheckedRadioButtonId());
//                createUser(binding.etFname.getText().toString(), binding.etLname.getText().toString(), binding.etEmail.getText().toString(),
//                        binding.etPhone.getText().toString(), radioButton.getText().toString());

                // TODO : sending Data to another activity using PArceble interface
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                PersonalParcebleData data = new PersonalParcebleData(binding.etFname.getText().toString(),
//                        binding.etLname.getText().toString(), binding.etEmail.getText().toString(), binding.etPhone.getText().toString());
//
//                intent.putExtra("parcebleData", data);
//                startActivity(intent);

                // TODO : sending Data to another activity using Seriazable interface
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                PersonalData data = new PersonalData(binding.etFname.getText().toString(),
                        binding.etLname.getText().toString(), binding.etEmail.getText().toString(), binding.etPhone.getText().toString());

                intent.putExtra("seriazableData", data);
                startActivity(intent);

            }
        });

    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater  menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        if (menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.alarm:
                Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.map:
                Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater  menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.alarm:
                Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.map:
                Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void createUser(String fName, String lName, String email, String phonoe, String gender){
        if (TextUtils.isEmpty(userId)) {
            userId = databaseReference.push().getKey();
        }
//        RadioButton radioButton = (RadioButton) findViewById(binding.rgGender.getCheckedRadioButtonId());
        personalData = new PersonalData(fName, lName, email, phonoe, gender);
        databaseReference.child(userId).setValue(personalData);
        addUserChangeListener();
        
    }

    private void addUserChangeListener(){
        databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PersonalData personalData = snapshot.getValue(PersonalData.class);
                Toast.makeText(MainActivity.this, personalData.getfName()+" "+personalData.getsName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}