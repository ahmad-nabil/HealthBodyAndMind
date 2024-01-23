package com.ahmad.healthbodyandmind.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ahmad.healthbodyandmind.BasalMetaBolicRate.BasalMetaBolicRate;
import com.ahmad.healthbodyandmind.bodymassindex.BodyMassIndex;
import com.ahmad.healthbodyandmind.BrainTrainer.BrainTrainer;
import com.ahmad.healthbodyandmind.R;
import com.ahmad.healthbodyandmind.settings.settings;
import com.ahmad.healthbodyandmind.stepscounter.StepCounter;
import com.ahmad.healthbodyandmind.databinding.ActivityHomeBinding;
public class Home extends AppCompatActivity implements View.OnClickListener {
ActivityHomeBinding homeBinding;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding =ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());
    setSupportActionBar(homeBinding.bar);
    homeBinding.bmiBtn.setOnClickListener(this);
    homeBinding.bmiIV.setOnClickListener(this);
    homeBinding.bmrBtn.setOnClickListener(this);
    homeBinding.calorie.setOnClickListener(this);
    homeBinding.stebcounterbtn.setOnClickListener(this);
    homeBinding.stebIV.setOnClickListener(this);
    homeBinding.braintrainBtn.setOnClickListener(this);
    homeBinding.BraintrainerImgview.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.setting) {
startActivity(new Intent(this, settings.class));
        } else if (itemId == R.id.exit) {
       finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == homeBinding.bmiBtn.getId() || v.getId() == homeBinding.bmiIV.getId()) {
            startActivity(new Intent(this, BodyMassIndex.class));
        } else if (v.getId() == homeBinding.bmrBtn.getId()||v.getId()==homeBinding.calorie.getId()) {
            startActivity(new Intent(this, BasalMetaBolicRate.class));
        } else if (v.getId() == homeBinding.stebcounterbtn.getId() || v.getId() == homeBinding.stebIV.getId()) {
            startActivity(new Intent(this, StepCounter.class));
        } else if (v.getId() == homeBinding.braintrainBtn.getId() || v.getId() == homeBinding.BraintrainerImgview.getId()) {
            startActivity(new Intent(this, BrainTrainer.class));
        }

}

}