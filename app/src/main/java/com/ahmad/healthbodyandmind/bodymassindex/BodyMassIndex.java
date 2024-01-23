package com.ahmad.healthbodyandmind.bodymassindex;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ahmad.healthbodyandmind.R;
import com.ahmad.healthbodyandmind.databinding.ActivityBodymassindexBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class BodyMassIndex extends AppCompatActivity {
    ActivityBodymassindexBinding BmiBinding;
    BmiViewModel viewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BmiBinding = ActivityBodymassindexBinding.inflate(getLayoutInflater());
        setContentView(BmiBinding.getRoot());
        viewModel = new ViewModelProvider(this).get(BmiViewModel.class);
        BmiBinding.calculatebmi.setOnClickListener(this::calculateBmi);
        viewModel.getBmiResult().observe(this, result -> {
            showDialog(result);
        });
    }


    private void calculateBmi(View view) {
        if (TextUtils.isEmpty(BmiBinding.setheight.getText()) || TextUtils.isEmpty(BmiBinding.setweight.getText())) {
            Toast.makeText(this, getString(R.string.pleasefillallinformation), Toast.LENGTH_LONG).show();
        } else {
            Double weight = Double.parseDouble(BmiBinding.setweight.getText().toString());
            Double height = Double.parseDouble(BmiBinding.setheight.getText().toString());
            viewModel.calculateBmi(weight, height);
        }
    }

    private void showDialog(Double result) {
        TextView textView = new TextView(this);
        textView.setText("Your Body mass Index is :\n" + result.toString());
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextSize(20f);

        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setView(textView).show();
    }
}