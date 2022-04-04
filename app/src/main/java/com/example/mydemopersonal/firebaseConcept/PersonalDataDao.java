package com.example.mydemopersonal.firebaseConcept;

import com.example.mydemopersonal.model.PersonalData;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class PersonalDataDao {
    private DatabaseReference databaseReference;

    public PersonalDataDao(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(PersonalData.class.getSimpleName());
    }

    public Task<Void> add(PersonalData data){
        return databaseReference.push().setValue(data);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

    public Query get(){
        return databaseReference;
    }
}
