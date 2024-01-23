package com.ahmad.healthbodyandmind.login;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {
private MutableLiveData <Boolean> authnicationResult=new MutableLiveData<>();
private FirebaseAuth auth;
public  LoginViewModel (){
auth=FirebaseAuth.getInstance();
}
public LiveData <Boolean> getAuthResult(){
    return authnicationResult;
}
public void Login(String email,String password){
    auth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    authnicationResult.setValue(true);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    authnicationResult.setValue(false);

                }
            });
}
public void restPassword(String Email,Activity activity){
    auth.sendPasswordResetEmail(Email).addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void unused) {
            Toast.makeText(activity, "link sent to your email", Toast.LENGTH_SHORT).show();
        }
    });
}
}
