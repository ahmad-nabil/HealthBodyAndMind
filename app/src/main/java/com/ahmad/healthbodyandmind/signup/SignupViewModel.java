package com.ahmad.healthbodyandmind.signup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupViewModel extends ViewModel {
    private MutableLiveData <Boolean>SignupResult=new MutableLiveData<>();
FirebaseAuth auth;
DatabaseReference reference;
public SignupViewModel (){
    auth=FirebaseAuth.getInstance();
reference=FirebaseDatabase.getInstance().getReference();
}
    public LiveData <Boolean>getResult(){
        return SignupResult;
    }
public void signupWithEmailAndPassword(String email ,String Password,signupCustomModel signupCustomModel){
auth.createUserWithEmailAndPassword(email, Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
    @Override
    public void onSuccess(AuthResult authResult) {
reference.child(auth.getUid()).setValue(signupCustomModel).addOnSuccessListener(new OnSuccessListener<Void>() {
    @Override
    public void onSuccess(Void unused) {
   SignupResult.setValue(true);
    }
}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        SignupResult.setValue(false);

    }
});
    }
}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        SignupResult.setValue(false);

    }
});
}

}
