package com.ahmad.healthbodyandmind.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmad.healthbodyandmind.Home.Home;
import com.ahmad.healthbodyandmind.R;
import com.ahmad.healthbodyandmind.databinding.ActivityLoginBinding;
import com.ahmad.healthbodyandmind.signup.signup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class login extends AppCompatActivity implements View.OnClickListener {
    private LoginViewModel loginViewModel;
    ActivityLoginBinding loginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        loginViewModel=new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.getAuthResult().observe(this, result ->
        {
if (result){
    startActivity(new Intent(this, Home.class));
}else {
    Toast.makeText(this, "login unsuccessful", Toast.LENGTH_SHORT).show();
}
        });
loginBinding.loginBtn.setOnClickListener(this);
loginBinding.restpasswordBtn.setOnClickListener(this);
loginBinding.signupBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       if (v.getId()==loginBinding.loginBtn.getId()){
if (TextUtils.isEmpty(loginBinding.EmailLogin.getText())||
        TextUtils.isEmpty(loginBinding.password.getText())){
    Toast.makeText(this, "please enter full data", Toast.LENGTH_SHORT).show();
        }else {
    String email=loginBinding.EmailLogin.getText().toString();
    String password=loginBinding.password.getText().toString();
    loginViewModel.Login(email,password);
        }
       }else if (v.getId()==loginBinding.restpasswordBtn.getId()){
           EditText editText=new EditText(this);
           MaterialAlertDialogBuilder rest=new MaterialAlertDialogBuilder(this);
           rest.setTitle("rest password");
           rest.setMessage("enter email");
           rest.setView(editText);
           rest.setPositiveButton("rest", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
              String email=editText.getText().toString();
              loginViewModel.restPassword(email,login.this);
               }
           });
           rest.show();}
       else if (v.getId()== R.id.signupBtn){
           startActivity(new Intent(this, signup.class));
       }

    }
}