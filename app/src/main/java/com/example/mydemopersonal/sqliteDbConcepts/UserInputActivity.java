package com.example.mydemopersonal.sqliteDbConcepts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityUserInputBinding;
import com.example.mydemopersonal.db.DBAdapter;
import com.example.mydemopersonal.model.PersonalData;
import com.example.mydemopersonal.model.Persons;
import com.example.mydemopersonal.utility.Util;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserInputActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityUserInputBinding binding;
    private DBAdapter dbAdapter;
    private Context context;
    private Cursor cursor;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private String imageDataBase64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_input);
        context = this;
        dbAdapter = new DBAdapter(context);
        dbAdapter.openDatabase();

        binding.btnSubmit.setOnClickListener(this);
        binding.btnShowPersonList.setOnClickListener(this);
        binding.imgView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSubmit:
                RadioButton radioButton = (RadioButton) findViewById(binding.rgGender.getCheckedRadioButtonId());
                dbAdapter.insertData(context, binding.etFname.getText().toString(), binding.etLname.getText().toString(), binding.etEmail.getText().toString(), binding.etPhone.getText().toString(), radioButton.getText().toString(), imageDataBase64, binding.etMname.getText().toString());
                break;
            case R.id.btnShowPersonList:
                startActivity(new Intent(context, PersonalDetailsList.class));
//                startActivity(new Intent(context, RecyclerviewActivity.class));
                break;

            case R.id.imgView:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                    }
                    else
                    {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                }
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            binding.imgView.setImageBitmap(bitmap);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
            byte[] imageData = outputStream.toByteArray();
            imageDataBase64 = Base64.encodeToString(imageData, Base64.DEFAULT);
        }
    }

//    public  List<Persons> getAllData(){
//        cursor = dbAdapter.getAllData(context);
//        Persons personalData = new Persons();
//        List<Persons> data = new ArrayList<>();
//        while (cursor.moveToNext()){
//            personalData.setFirstName(cursor.getString(0)); // fname
//            personalData.setLastName(cursor.getString(1)); // lname
//            personalData.setEmail(cursor.getString(2)); // email
//            personalData.setPhone(cursor.getString(3)); // phone
//            personalData.setGender(cursor.getString(4)); // gender
//            data.add(personalData);
//        }
//        return data;
//    }

    @Override
    protected void onResume() {
        super.onResume();
//        getAllData();
    }
}