package com.ahmad.healthbodyandmind.bodymassindex;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BmiViewModel extends ViewModel {
    private MutableLiveData <Double> bmiResult=new MutableLiveData<>();

    public MutableLiveData<Double> getBmiResult() {
        return bmiResult;
    }
    public void calculateBmi(double weight,double height){
        double heightInMeters = height / 100.0;
        double bmi= weight / (heightInMeters * heightInMeters);
        bmiResult.setValue(bmi);
    }
}
