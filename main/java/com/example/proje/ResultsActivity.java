package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class ResultsActivity extends AppCompatActivity {

    TextView percentText, rightText, wrongText;
    Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Sets Action Bar title with protection against NullPointerException
        Objects.requireNonNull(getSupportActionBar()).setTitle("Results");
        init();
    }

    void init() {
        percentText = findViewById(R.id.percentText);
        rightText = findViewById(R.id.rightText);
        wrongText = findViewById(R.id.wrongText);
        okButton = findViewById(R.id.okButton);

        int totalRights = getIntent().getIntExtra("totalRights", 0);
        int totalWrongs = getIntent().getIntExtra("totalWrongs", 0);
        double percentOfRights = ((double)totalRights / (double)(totalWrongs + totalRights)) * 100;

        rightText.setText(getString(R.string.text_for_rights, totalRights));
        wrongText.setText(getString(R.string.text_for_wrongs, totalWrongs));
        percentText.setText(getString(R.string.text_for_percent, (int)percentOfRights));


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}