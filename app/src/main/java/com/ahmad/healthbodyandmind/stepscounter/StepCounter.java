package com.ahmad.healthbodyandmind.stepscounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;

import com.ahmad.healthbodyandmind.databinding.ActivityStepCounterBinding;

public class StepCounter extends AppCompatActivity {
private SensorManager sensorManager;
private Sensor steps_sensor;
    private StepCounterService stepCounterService;
int totalsteps=0;
    private boolean SensorServiceBound = false;
StepViewModel viewModel;
ActivityStepCounterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStepCounterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new ViewModelProvider(this).get(StepViewModel.class);
        //Bind to step counter Service
        Intent service=new Intent(this, StepCounterService.class);
        bindService(service,serviceConnection, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            StepCounterService.StepCounterBinder binder= (StepCounterService.StepCounterBinder) service;
            stepCounterService=binder.getservice();
            stepCounterService.setStepViewModel(viewModel);
            SensorServiceBound =true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        SensorServiceBound =false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    if (SensorServiceBound){
        unbindService(serviceConnection);
    SensorServiceBound=false;
    }
    }

    @Override
    protected void onResume() {
        super.onResume();
viewModel.getStepCount().observe(this, new Observer<Integer>() {
    @Override
    public void onChanged(Integer StepsCount) {
binding.steps.setText("Steps  :"+ String.valueOf(StepsCount));
    }
});
    }
}