package com.ahmad.healthbodyandmind.BasalMetaBolicRate;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BmrViewModel extends ViewModel {
    private MutableLiveData <Double> Result=new MutableLiveData<>();

    public MutableLiveData<Double> getResult() {
        return Result;
    }
    public void calculateBmr(double weight,double height,int age,boolean isMale){
     double bmr;
        if (isMale){
bmr=10 * weight + 6.25 * height - 5 * age + 5;
        }else {
            bmr = 10 * weight + 6.25 * height - 5 * age - 161;
        }
        Result.setValue(bmr);
    }
}
