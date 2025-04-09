package com.prac.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ensure the IDs in the layout file match these names
        editTextName = findViewById(R.id.nameInput);
        buttonStart = findViewById(R.id.startQuizBtn);

        // Retrieve saved username from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("quizApp", MODE_PRIVATE);
        String savedName = prefs.getString("username", "");
        editTextName.setText(savedName);

        // Start quiz button click listener
        buttonStart.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            if (!name.isEmpty()) {
                // Save the username to SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("username", name);
                editor.apply();

                // Start the QuizActivity
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            } else {
                // Show a toast message if no name is entered
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
