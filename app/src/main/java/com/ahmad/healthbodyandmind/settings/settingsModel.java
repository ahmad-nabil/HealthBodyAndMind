package com.ahmad.healthbodyandmind.settings;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmad.healthbodyandmind.signup.signupCustomModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class settingsModel extends ViewModel {
     MutableLiveData<signupCustomModel>data=new MutableLiveData<>();
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    public MutableLiveData<signupCustomModel> getData() {
        return data;
    }

    public void setData(signupCustomModel user) {

       data.setValue(user);
    }

    public void setData(MutableLiveData<signupCustomModel> data) {
        this.data = data;
    }

    public void fetchDataFromFirebase(String UID){
        databaseReference.child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                signupCustomModel signupCustomModel=snapshot.getValue(signupCustomModel.class);
                 setData(signupCustomModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void updateData(String UID,Map signupCustomModel){
        databaseReference.child(UID).updateChildren(signupCustomModel);
    }

}
