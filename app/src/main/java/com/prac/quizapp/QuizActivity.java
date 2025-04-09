package com.prac.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    TextView questionText;
    RadioGroup optionsGroup;
    RadioButton option1, option2, option3, option4;
    Button submitBtn;
    ProgressBar progressBar;

    int currentIndex = 0;
    int score = 0;
    boolean answered = false;

    String[] questions = {
        "What is the capital of France?",
        "2 + 2 = ?",
        "Who wrote Hamlet?"
    };

    String[][] options = {
        {"London", "Berlin", "Paris", "Madrid"},
        {"3", "4", "5", "6"},
        {"Shakespeare", "Dickens", "Hemingway", "Tolkien"}
    };

    int[] correctAnswers = {2, 1, 0}; // Index of correct answer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize Views
        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        submitBtn = findViewById(R.id.submitBtn);
        progressBar = findViewById(R.id.progressBar);

        loadQuestion();

        submitBtn.setOnClickListener(v -> {
            if (!answered) {
                int selectedId = optionsGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    return;
                }

                answered = true;
                RadioButton selected = findViewById(selectedId);
                int index = optionsGroup.indexOfChild(selected);

                if (index == correctAnswers[currentIndex]) {
                    selected.setBackgroundColor(Color.GREEN);
                    score++;
                } else {
                    selected.setBackgroundColor(Color.RED);
                    getCorrectRadioButton().setBackgroundColor(Color.GREEN);
                }

                submitBtn.setText("Next");

            } else {
                currentIndex++;
                if (currentIndex < questions.length) {
                    loadQuestion();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void loadQuestion() {
        answered = false;
        optionsGroup.clearCheck();
        resetOptionColors();

        questionText.setText(questions[currentIndex]);
        option1.setText(options[currentIndex][0]);
        option2.setText(options[currentIndex][1]);
        option3.setText(options[currentIndex][2]);
        option4.setText(options[currentIndex][3]);

        progressBar.setProgress((currentIndex + 1) * 100 / questions.length);
        submitBtn.setText("Submit");
    }

    private void resetOptionColors() {
        option1.setBackgroundColor(Color.TRANSPARENT);
        option2.setBackgroundColor(Color.TRANSPARENT);
        option3.setBackgroundColor(Color.TRANSPARENT);
        option4.setBackgroundColor(Color.TRANSPARENT);
    }

    private RadioButton getCorrectRadioButton() {
        int correctIndex = correctAnswers[currentIndex];
        return (RadioButton) optionsGroup.getChildAt(correctIndex);
    }
}
