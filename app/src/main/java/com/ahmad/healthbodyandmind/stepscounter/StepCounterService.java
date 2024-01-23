package com.ahmad.healthbodyandmind.stepscounter;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class StepCounterService extends Service implements SensorEventListener {
final IBinder binder=new StepCounterBinder();
    private SensorManager sensorManager;
    private Sensor stepSensor;

    private StepViewModel stepViewModel;

    @Override
    public void onCreate() {
        super.onCreate();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager!=null){
            stepSensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            if (stepSensor!=null){
                sensorManager.registerListener(this,stepSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class StepCounterBinder extends Binder{
public StepCounterService getservice(){
    return StepCounterService.this;
}


}
    @Override
    public void onSensorChanged(SensorEvent event) {
if (event.sensor.getType()==Sensor.TYPE_STEP_COUNTER){
    stepViewModel.incrementStepCount();
}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    sensorManager.unregisterListener(this,stepSensor);
    }
    public void setStepViewModel(StepViewModel viewModel){
        this.stepViewModel=viewModel;
    }
}
