package com.prac.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    TextView scoreText;
    Button newQuizBtn, finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreText = findViewById(R.id.scoreText);
        newQuizBtn = findViewById(R.id.newQuizBtn);
        finishBtn = findViewById(R.id.finishBtn);

        int score = getIntent().getIntExtra("score", 0);
        scoreText.setText("Your Score: " + score);

        newQuizBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        finishBtn.setOnClickListener(v -> finishAffinity());
    }
}
