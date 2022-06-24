package com.example.mydemopersonal.firebaseConcept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityPersonalDetailBinding;
import com.example.mydemopersonal.model.PersonalData;
import com.example.mydemopersonal.utility.Util;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

public class PersonalDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityPersonalDetailBinding binding;
    private RadioButton radioButton;
    private PersonalDataDao personalDataDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_personal_detail);
        personalDataDao = new PersonalDataDao();

        //TODO : Setting default data
//        binding.etFname.setText("Rakesh Kumar");
//        binding.etLname.setText("Sharma");
//        binding.etEmail.setText("kumarakesharma@gmail.com");
//        binding.etPhone.setText("9625033237");

        binding.btnSubmit.setOnClickListener(PersonalDetailActivity.this);
        binding.btnUpdate.setOnClickListener(PersonalDetailActivity.this);
        binding.btnRemove.setOnClickListener(PersonalDetailActivity.this);
        binding.tvList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                radioButton = (RadioButton) findViewById(binding.rgGender.getCheckedRadioButtonId());
                //TODO : For insertion
                PersonalData personalData = new PersonalData(binding.etFname.getText().toString(), binding.etLname.getText().toString(),
                        binding.etEmail.getText().toString(), binding.etPhone.getText().toString(), radioButton.getText().toString());
                personalDataDao.add(personalData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Util.showCustomToast(PersonalDetailActivity.this, "Inserted successfully");
                        binding.etFname.setText("");
                        binding.etLname.setText("");
                        binding.etEmail.setText("");
                        binding.etPhone.setText("");
                        radioButton.setChecked(false);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Util.showCustomToast(PersonalDetailActivity.this, e.getMessage());
                    }
                });
                break;

            case R.id.btnUpdate:
                // TODO : FOr updation
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("fName", binding.etFname.getText().toString());
                hashMap.put("sName", binding.etLname.getText().toString());
                hashMap.put("email", binding.etEmail.getText().toString());
                hashMap.put("phone", binding.etPhone.getText().toString());
                hashMap.put("gender", radioButton.getText().toString());

                personalDataDao.update("-Mhr_hi6nCj3holiJ-sh", hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Util.showCustomToast(PersonalDetailActivity.this, "Record updated.");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Util.showCustomToast(PersonalDetailActivity.this, e.getMessage());
                    }
                });
                break;

            case R.id.btnRemove:
                // TODO : For Deletion
                personalDataDao.remove("-Mhr_hi6nCj3holiJ-sh").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Util.showCustomToast(PersonalDetailActivity.this, "Record removed.");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Util.showCustomToast(PersonalDetailActivity.this, e.getMessage());
                    }
                });
                break;

            case R.id.tvList:
                startActivity(new Intent(PersonalDetailActivity.this, ListActivity.class));
                break;
        }
    }
}