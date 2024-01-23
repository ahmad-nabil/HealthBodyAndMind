package com.ahmad.healthbodyandmind.BrainTrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.ahmad.healthbodyandmind.R;
import com.ahmad.healthbodyandmind.databinding.ActivityBrainTrainerBinding;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class BrainTrainer extends AppCompatActivity {
ActivityBrainTrainerBinding binding;
BrainTrainerModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBrainTrainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new ViewModelProvider(this).get(BrainTrainerModel.class);
        binding.startButton.setOnClickListener(v->{
            viewModel.startgame();
            binding.startButton.setVisibility(View.GONE);
            binding.answerButton0.setVisibility(View.VISIBLE);
            binding.answerButton1.setVisibility(View.VISIBLE);
            binding.answerButton2.setVisibility(View.VISIBLE);
            binding.answerButton3.setVisibility(View.VISIBLE);
            locationAnswer(viewModel.Answers);
        });
        checkanswer();
        observeViewModel();
    }

    private void checkanswer() {
        binding.answerButton0.setOnClickListener(v -> {
            viewModel.checkAnswer(Integer.parseInt(binding.answerButton0.getText().toString()));
            locationAnswer(viewModel.Answers);
        });
        binding.answerButton1.setOnClickListener(v -> {
            viewModel.checkAnswer(Integer.parseInt(binding.answerButton1.getText().toString()));
            locationAnswer(viewModel.Answers);
        });
        binding.answerButton2.setOnClickListener(v -> {
            viewModel.checkAnswer(Integer.parseInt(binding.answerButton2.getText().toString()));
            locationAnswer(viewModel.Answers);
        });
        binding.answerButton3.setOnClickListener(v -> {
            viewModel.checkAnswer(Integer.parseInt(binding.answerButton3.getText().toString()));
            locationAnswer(viewModel.Answers);
        });
    }

    private void observeViewModel() {
        viewModel.getGameActive().observe(this, gameActive -> {
            if (gameActive) { startTimer(); }
            else { endGame(); }
        });

        viewModel.getQuestions().observe(this,question ->{
            binding.questionTextView.setText(question);
        });

      viewModel.getScore().observe(this,score->{
          binding.score.setText("score ="+score);
      });

    }
    private void startTimer() {
    new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                binding.timerTextView.setText(Integer.toString((int)millisUntilFinished/1000));
            }
            @Override
            public void onFinish() {

            }
        }.start();    }

    private void endGame() {
        binding.startButton.setVisibility(View.VISIBLE);
        binding.answerButton0.setVisibility(View.GONE);
        binding.answerButton1.setVisibility(View.GONE);
        binding.answerButton2.setVisibility(View.GONE);
        binding.answerButton3.setVisibility(View.GONE);



    }
    private void locationAnswer(ArrayList<Integer> answers) {
        binding.answerButton0.setText(answers.get(0).toString());
        binding.answerButton1.setText(answers.get(1).toString());
        binding.answerButton2.setText(answers.get(2).toString());
        binding.answerButton3.setText(answers.get(3).toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.getGameActive().setValue(false);
        endGame();
    }

}