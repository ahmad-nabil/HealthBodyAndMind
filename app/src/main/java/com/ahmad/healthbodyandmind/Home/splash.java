package com.ahmad.healthbodyandmind.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ahmad.healthbodyandmind.BrainTrainer.BrainTrainer;
import com.ahmad.healthbodyandmind.R;
import com.ahmad.healthbodyandmind.login.login;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splash.this, login.class));
            }
        },3000);
    }
}