package com.example.mydemopersonal.sqliteDbConcepts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityPersonalDetailBinding;
import com.example.mydemopersonal.databinding.ActivityPersonalDetailsListBinding;
import com.example.mydemopersonal.databinding.UpdateDialogBinding;
import com.example.mydemopersonal.db.DBAdapter;
import com.example.mydemopersonal.interfaces.ListviewListener;
import com.example.mydemopersonal.interfaces.OnItemClickListener;
import com.example.mydemopersonal.model.PersonalData;
import com.example.mydemopersonal.model.Persons;
import com.example.mydemopersonal.utility.Util;
import com.google.firebase.database.core.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

public class PersonalDetailsList extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private ActivityPersonalDetailsListBinding binding;
    private ListAdapter listAdapter;
    private Cursor cursor;
    private DBAdapter dbAdapter;
    private Context context;
    private int clickedPosition;
    RadioButton radioButton;
    private ListAdapter adapter;
    private String selectedRadioButtonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_personal_details_list);
        context = this;
        dbAdapter = new DBAdapter(context);
        dbAdapter.openDatabase();
        loadData();
        //todo : listener on listview
        binding.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Persons persons = (Persons) adapterView.getItemAtPosition(position);
                Util.showCustomToast(context, persons.getFirstName());
            }
        });

        binding.list.setOnItemLongClickListener(this);

        registerForContextMenu(binding.list);
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

//                tempList.add(personalData);
                data.add(personalData);
            }while (cursor.moveToNext());
        }
//        cursor.close();
        return data;
    }

    private void loadData(){
        if (getAllPersonData().size() == 0) binding.llNoData.setVisibility(View.VISIBLE);
        // todo : seeting data to the list and adding listener to the listview

//        listAdapter = new ListAdapter(PersonalDetailsList.this, getAllPersonData(), new OnItemClickListener() {
//            @Override
//            public void onItemClick(List<Persons> personsList, int position) {
//                Util.showCustomToast(PersonalDetailsList.this, ""+position);
//            }
//        });

        listAdapter = new ListAdapter(PersonalDetailsList.this, getAllPersonData());
        binding.list.setAdapter(listAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemDelete:
                cursor.moveToPosition(clickedPosition);
                String rowId = cursor.getString(0);
                dbAdapter.deleteSingleRecord(PersonalDetailsList.this, rowId);

                loadData();
                break;
            case R.id.itemUpdate:
                showUpdateDialog();
                break;

            case R.id.itemDeleteAllRecords:
                dbAdapter.deleteAllRecords(PersonalDetailsList.this);
                loadData();
                break;
        }
        return true;
    }

    private void showUpdateDialog(){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        UpdateDialogBinding binding = (UpdateDialogBinding) DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.update_dialog, null, false);
        dialog.setContentView(binding.getRoot());
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.setCancelable(false);

        cursor.moveToPosition(clickedPosition);
        String rowId = cursor.getString(0);
        String fName = cursor.getString(1);
        String lName = cursor.getString(2);
        String email = cursor.getString(3);
        String phoneNo = cursor.getString(4);;
        String gender = cursor.getString(5);

        binding.etFname.setText(fName);
        binding.etLname.setText(lName);
        binding.etEmail.setText(email);
        binding.etPhone.setText(phoneNo);

        binding.rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                RadioButton radioButton = (RadioButton) findViewById(binding.rgGender.getCheckedRadioButtonId());
//                selectedRadioButtonText = radioButton.getText().toString();
            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.upDataRecord(context, rowId, fName, lName, email, phoneNo, "Male");
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        clickedPosition = i;
        return false;
    }
}