package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Objects;

public class WelcomeActivity extends AppCompatActivity {

    ImageButton playIButton, settingsIButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //hides Action Bar with protection against NullPointerException
        Objects.requireNonNull(getSupportActionBar()).hide();
        init();
    }

    void init() {
        playIButton = findViewById(R.id.playIButton);
        settingsIButton = findViewById(R.id.settingsIButton);


        playIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });

        settingsIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });


    }
}