package com.ahmad.healthbodyandmind.BasalMetaBolicRate;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ahmad.healthbodyandmind.R;
import com.ahmad.healthbodyandmind.databinding.ActivityBasalMetaBolicRateBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class BasalMetaBolicRate extends AppCompatActivity {
    private BmrViewModel bmrViewModel;
    ActivityBasalMetaBolicRateBinding bmrBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bmrBinding = ActivityBasalMetaBolicRateBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(bmrBinding.getRoot());
        bmrViewModel = new ViewModelProvider(this).get(BmrViewModel.class);
        bmrBinding.calculatebmr.setOnClickListener(this::calculatevalue);
        bmrViewModel.getResult().observe(this, result -> {
       showDialog(result);
        });
    }

    private void calculatevalue(View calculatebmrBtn) {
        if (TextUtils.isEmpty(bmrBinding.Age.getText()) ||
                TextUtils.isEmpty(bmrBinding.setweight.getText()) ||
                TextUtils.isEmpty(bmrBinding.setheight.getText()) ||
                bmrBinding.gender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, getString(R.string.pleasefillallinformation), Toast.LENGTH_LONG).show();

        } else {
            double weight = Double.parseDouble(bmrBinding.setweight.getText().toString());
            double height = Double.parseDouble(bmrBinding.setheight.getText().toString());
            int age = Integer.parseInt(bmrBinding.Age.getText().toString());
            boolean isMale = bmrBinding.gender.getCheckedRadioButtonId() == bmrBinding.male.getId();
            bmrViewModel.calculateBmr(weight, height, age, isMale);
        }

    }
    private void showDialog(Double result) {
        TextView textView = new TextView(this);
        textView.setText("your BMR is:\n" +result.toString());
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setView(textView).show();
    }
}