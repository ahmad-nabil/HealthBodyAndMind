package com.ahmad.healthbodyandmind.BrainTrainer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmad.healthbodyandmind.databinding.ActivityBrainTrainerBinding;

import java.util.ArrayList;
import java.util.Random;

public class BrainTrainerModel extends ViewModel {
    private MutableLiveData<String> questions=new MutableLiveData<>();
    private MutableLiveData<Boolean> gameActive=new MutableLiveData<>();
    private MutableLiveData<Integer> score = new MutableLiveData<>();

    int AnswerLocation;
    int sum;
ArrayList <Integer>Answers=new ArrayList<>();



    public MutableLiveData<Boolean> getGameActive() {
        return gameActive;
    }

    public MutableLiveData<Integer> getScore() {
        return score;
    }



    public MutableLiveData<String> getQuestions() {
        return questions;
    }


    public void startgame(){
        gameActive.setValue(true);
        score.setValue(0);
        newquestion();
    }

    private void newquestion() {
        Random random=new Random();
        int a=random.nextInt(30);
        int b=random.nextInt(30);
        questions.setValue(a + "+"+ b);
  sum=a+b;
        AnswerLocation=random.nextInt(4);
        for (int i=0;i<4;i++){
            if (i==AnswerLocation){
                Answers.add(i,sum);

            }else {
                int wrong=random.nextInt(44);
                while (wrong==sum){
                    wrong=random.nextInt(94);
                }
                Answers.add(i,wrong);
            }
        }

    }



    public void checkAnswer(int chosenAnswer) {
        score.setValue((chosenAnswer == sum) ? score.getValue() + 1 : score.getValue());
        newquestion();
    }
    public void endGame() { gameActive.setValue(false); }


}
