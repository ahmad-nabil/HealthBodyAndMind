package com.ahmad.healthbodyandmind.stepscounter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StepViewModel extends ViewModel {
    private MutableLiveData<Integer> stepCount = new MutableLiveData<>();
    public StepViewModel() {
        stepCount.setValue(0);
    }

    public MutableLiveData<Integer> getStepCount() {
        return stepCount;
    }
    public void incrementStepCount() {
        Integer currentCount = stepCount.getValue();
        if (currentCount != null) {
            stepCount.setValue(currentCount + 1);
        }

    }
}
