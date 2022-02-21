package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    RadioButton easyButton, mediumButton, hardButton;
    ImageView easyImage, mediumImage, hardImage;
    TextView levelText;
    String level = "easy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Sets Action Bar title with protection against NullPointerException
        Objects.requireNonNull(getSupportActionBar()).setTitle("Settings");
        init();

    }

    void init() {
        easyButton = findViewById(R.id.easyButton);
        mediumButton = findViewById(R.id.mediumButton);
        hardButton = findViewById(R.id.hardButton);
        levelText = findViewById(R.id.levelText);
        easyImage = findViewById(R.id.easyImage);
        mediumImage = findViewById(R.id.mediumImage);
        hardImage = findViewById(R.id.hardImage);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = "easy";
                easyImage.setVisibility(View.VISIBLE);
                mediumImage.setVisibility(View.INVISIBLE);
                hardImage.setVisibility(View.INVISIBLE);
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = "medium";
                easyImage.setVisibility(View.INVISIBLE);
                mediumImage.setVisibility(View.VISIBLE);
                hardImage.setVisibility(View.INVISIBLE);

            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = "hard";
                easyImage.setVisibility(View.INVISIBLE);
                mediumImage.setVisibility(View.INVISIBLE);
                hardImage.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(SettingsActivity.this, QuestionActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }
}