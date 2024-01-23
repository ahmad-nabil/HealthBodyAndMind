package com.ahmad.healthbodyandmind.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.ahmad.healthbodyandmind.Home.Home;
import com.ahmad.healthbodyandmind.databinding.ActivitySignupBinding;

public class signup extends AppCompatActivity implements View.OnClickListener {
ActivitySignupBinding signupBinding;
SignupViewModel signupViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signupBinding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(signupBinding.getRoot());
        signupViewModel=new ViewModelProvider(this).get(SignupViewModel.class);
        signupViewModel.getResult().observe(this, result -> {
            if (result){
                startActivity(new Intent(this, Home.class));
            }else {
                Toast.makeText(this, "try again", Toast.LENGTH_SHORT).show();
            }
        }

        );
  signupBinding.btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        signupCustomModel customModel;
        if (v.getId()==signupBinding.btnRegister.getId()){
            if (TextUtils.isEmpty(signupBinding.emailEt.getText())
            ||TextUtils.isEmpty(signupBinding.NameEt.getText())
            ||TextUtils.isEmpty(signupBinding.passwordEt.getText())
            ||TextUtils.isEmpty(signupBinding.phoneEt.getText())){
                Toast.makeText(this, "fill all information please", Toast.LENGTH_SHORT).show();
            }else {
                String name=signupBinding.NameEt.getText().toString();
                String email=signupBinding.emailEt.getText().toString();
                String password=signupBinding.passwordEt.getText().toString();
                String phone=signupBinding.phoneEt.getText().toString();
                customModel=new signupCustomModel(name,phone,email,password);
signupViewModel.signupWithEmailAndPassword(email,password,customModel);
            }
        }
    }
}