package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    TextView scriptText;
    Button optionButton1, optionButton2, optionButton3, optionButton4;
    ConstraintLayout constraintLayout;
    ProgressBar progressBar;

    Random random = new Random();
    int questionNumber;
    int totalRights, totalWrongs;
    String level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Catch the Tongue!");
        init();
    }


    void init() {
        scriptText = findViewById(R.id.scriptText);
        optionButton1 = findViewById(R.id.option1);
        optionButton2 = findViewById(R.id.option2);
        optionButton3 = findViewById(R.id.option3);
        optionButton4 = findViewById(R.id.option4);
        constraintLayout = findViewById(R.id.constraintLayout);
        progressBar = findViewById(R.id.progressBar);

        initEasyQuestions();
        Collections.shuffle(easyQuestions);  //in order to display different list of questions each time
        embedCorrectAnswerToOptions(easyQuestions);
        initMediumQuestions();
        Collections.shuffle(mediumQuestions);
        embedCorrectAnswerToOptions(mediumQuestions);
        initHardQuestions();
        Collections.shuffle(hardQuestions);
        embedCorrectAnswerToOptions(hardQuestions);


        questionNumber = -1;
        totalWrongs = 0; totalRights = 0; //for ResultsActivity

        level = getIntent().getStringExtra("level"); //retrieves level from SettingsActivity

        //level is set to "easy" as default
        if (level == null) {
            level = "easy";
        }

        //first display of questions
        switch (level) {
            case "easy":
                nextQuestion(easyQuestions);
                break;
            case "medium":
                nextQuestion(mediumQuestions);
                break;
            case "hard":
                nextQuestion(hardQuestions);
                break;
        }


        optionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if there are no questions left, goes to ResultsActivity
                gotoResultsActivity();

                switch (level) {
                    case "easy":
                        if (optionButton1.getText().equals(easyQuestions.get(questionNumber).getCorrectAnswer())) {
                            correctSnackBarDisplay();
                        } else
                            incorrectSnackBarDisplay();
                        break;
                    case "medium":
                        if (optionButton1.getText().equals(mediumQuestions.get(questionNumber).getCorrectAnswer())) {
                            correctSnackBarDisplay();
                        } else
                            incorrectSnackBarDisplay();
                        break;
                    case "hard":
                        if (optionButton1.getText().equals(hardQuestions.get(questionNumber).getCorrectAnswer()))
                            correctSnackBarDisplay();
                        else
                            incorrectSnackBarDisplay();
                        break;
                }

            }
        });

        optionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoResultsActivity();

                switch (level) {
                    case "easy":
                        if (optionButton2.getText().equals(easyQuestions.get(questionNumber).getCorrectAnswer())) {
                            correctSnackBarDisplay();
                        } else
                            incorrectSnackBarDisplay();
                        break;
                    case "medium":
                        if (optionButton2.getText().equals(mediumQuestions.get(questionNumber).getCorrectAnswer())) {
                            correctSnackBarDisplay();
                        } else
                            incorrectSnackBarDisplay();
                        break;
                    case "hard":
                        if (optionButton2.getText().equals(hardQuestions.get(questionNumber).getCorrectAnswer()))
                            correctSnackBarDisplay();
                        else
                            incorrectSnackBarDisplay();
                        break;
                }
            }
        });

        optionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoResultsActivity();

                switch (level) {
                    case "easy":
                        if (optionButton3.getText().equals(easyQuestions.get(questionNumber).getCorrectAnswer())) {
                            correctSnackBarDisplay();
                        } else
                            incorrectSnackBarDisplay();
                        break;
                    case "medium":
                        if (optionButton3.getText().equals(mediumQuestions.get(questionNumber).getCorrectAnswer())) {
                            correctSnackBarDisplay();
                        } else
                            incorrectSnackBarDisplay();
                        break;
                    case "hard":
                        if (optionButton3.getText().equals(hardQuestions.get(questionNumber).getCorrectAnswer()))
                            correctSnackBarDisplay();
                        else
                            incorrectSnackBarDisplay();
                        break;
                }
            }
        });


        optionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoResultsActivity();

                switch (level) {
                    case "easy":
                        if (optionButton4.getText().equals(easyQuestions.get(questionNumber).getCorrectAnswer())) {
                            correctSnackBarDisplay();
                        } else
                            incorrectSnackBarDisplay();
                        break;
                    case "medium":
                        if (optionButton4.getText().equals(mediumQuestions.get(questionNumber).getCorrectAnswer())) {
                            correctSnackBarDisplay();
                        } else
                            incorrectSnackBarDisplay();
                        break;
                    case "hard":
                        if (optionButton4.getText().equals(hardQuestions.get(questionNumber).getCorrectAnswer()))
                            correctSnackBarDisplay();
                        else
                            incorrectSnackBarDisplay();
                        break;
                }
            }
        });
    }


    List<String> correctAnswerResponses = Arrays.asList("True!", "Correct!", "Right!", "Found it!", "Yes!");
    List<String> incorrectAnswerResponses = Arrays.asList("Not correct!", "Incorrect answer!", "Sorry, that's not it!", "No, try again!");

    List<GivenQuestion> easyQuestions;
    List<GivenQuestion> mediumQuestions;
    List<GivenQuestion> hardQuestions;

    List<String> allOptions = Arrays.asList("turkish", "german", "italian", "spanish", "chinese", "french", "arabic", "russian", "greek", "korean", "portuguese",
            "dutch", "swedish", "hindi", "japanese", "ukrainian", "latin", "bengali", "polish", "romanian", "tagalog", "czech",
            "catalan", "indonesian", "malayalam", "persian", "georgian", "hungarian", "kazakh", "armenian", "uzbek", "welsh", "norwegian");


    //loads next question to buttons
    void nextQuestion(List<GivenQuestion> givenQuestions) {
        questionNumber++;
        scriptText.setText(givenQuestions.get(questionNumber).getTextId());
        optionButton1.setText(givenQuestions.get(questionNumber).getOption1());
        optionButton2.setText(givenQuestions.get(questionNumber).getOption2());
        optionButton3.setText(givenQuestions.get(questionNumber).getOption3());
        optionButton4.setText(givenQuestions.get(questionNumber).getOption4());

        progressBar.incrementProgressBy(100/givenQuestions.size());
    }

    void initEasyQuestions() {
        easyQuestions = Arrays.asList(
                new GivenQuestion(R.string.e_text_german, "german", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.e_text_turkish, "turkish", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.e_text_italian, "italian", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.e_text_spanish, "spanish", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.e_text_french, "french", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.e_text_arabic, "arabic", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.e_text_russian, "russian", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.e_text_greek, "greek", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.e_text_korean, "korean", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.e_text_portuguese, "portuguese", genOption(), genOption(), genOption(), genOption()));
    }

    void initMediumQuestions() {
        mediumQuestions = Arrays.asList(
                new GivenQuestion(R.string.m_text_dutch, "dutch", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.m_text_swedish, "swedish", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.m_text_hindi, "hindi", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.m_text_japanese, "japanese", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.m_text_ukrainian, "ukrainian", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.m_text_latin, "latin", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.m_text_bengali, "bengali", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.m_text_polish, "polish", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.m_text_romanian, "romanian", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.m_text_tagalog, "tagalog", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.m_text_czech, "czech", genOption(), genOption(), genOption(), genOption()));
    }

    void initHardQuestions() {
        hardQuestions = Arrays.asList(
                new GivenQuestion(R.string.h_text_armenian, "armenian", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.h_text_catalan, "catalan", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.h_text_georgian, "georgian", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.h_text_hungarian, "hungarian", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.h_text_indonesian, "indonesian", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.h_text_malayalam, "malayalam", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.h_text_welsh, "welsh", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.h_text_norwegian, "norwegian", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.h_text_persian, "persian", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.h_text_swahili, "swahili", genOption(), genOption(), genOption(), genOption()),
                new GivenQuestion(R.string.h_text_uzbek, "uzbek", genOption(), genOption(), genOption(), genOption()));
    }


    List<String> previousOptions = new ArrayList<>();

    String genOption() {
        // TODO: incomplete attempt to prevent duplicate options
        String option = allOptions.get(random.nextInt(allOptions.size()));

        if (previousOptions.contains(option)) {
            genOption();
        } else
            previousOptions.add(option);

        if (previousOptions.size() == 4)
            previousOptions.clear();

        return option;
    }

    //makes sure that one of the options has the correct answer
    void embedCorrectAnswerToOptions(List<GivenQuestion> givenQuestions) {
        for (int i = 0; i < givenQuestions.size(); i++) {

            String correctAnswer = givenQuestions.get(i).getCorrectAnswer();

            //if none of the created options has the correct answer already
            if (givenQuestions.get(i).getOption1().equals(correctAnswer) ||
                    givenQuestions.get(i).getOption2().equals(correctAnswer) ||
                            givenQuestions.get(i).getOption3().equals(correctAnswer) ||
                                    givenQuestions.get(i).getOption4().equals(correctAnswer)) {
                continue;
            } else {
                //one of the 4 options is chosen and the correct answer is set to that option
                switch (random.nextInt(4)) {
                    case 0:
                        givenQuestions.get(i).setOption1(correctAnswer);
                        break;
                    case 1:
                        givenQuestions.get(i).setOption2(correctAnswer);
                        break;
                    case 2:
                        givenQuestions.get(i).setOption3(correctAnswer);
                        break;
                    case 3:
                        givenQuestions.get(i).setOption4(correctAnswer);
                        break;
                }
            }
        }
    }



    void correctSnackBarDisplay() {
        totalRights++;

        Snackbar snackbar = Snackbar.make(constraintLayout,
                //in order to display different responses each time
                correctAnswerResponses.get(random.nextInt(correctAnswerResponses.size())),
                Snackbar.LENGTH_LONG).setAction("NEXT", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (level) {
                    case "easy":
                        nextQuestion(easyQuestions);
                        break;
                    case "medium":
                        nextQuestion(mediumQuestions);
                        break;
                    case "hard":
                        nextQuestion(hardQuestions);
                        break;
                }
            }
        });

        //SnackBar anchored on top of ProgressBar
        snackbar.setAnchorView(progressBar);
        snackbar.setActionTextColor(Color.parseColor("#E7831E"));
        snackbar.show();
    }

    void incorrectSnackBarDisplay() {
        totalWrongs++;

        Snackbar snackbar = Snackbar.make(constraintLayout,
                incorrectAnswerResponses.get(random.nextInt(incorrectAnswerResponses.size())),
                Snackbar.LENGTH_LONG).setAction("SKIP?", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (level) {
                    case "easy":
                        nextQuestion(easyQuestions);
                        break;
                    case "medium":
                        nextQuestion(mediumQuestions);
                        break;
                    case "hard":
                        nextQuestion(hardQuestions);
                        break;
                }
            }
        });

        snackbar.setAnchorView(progressBar);
        snackbar.setActionTextColor(Color.parseColor("#E7831E"));
        snackbar.show();
    }


    //if there are no questions left, goes to ResultsActivity
    void gotoResultsActivity() {
        if (easyQuestions.size() == questionNumber + 1 || mediumQuestions.size() == questionNumber + 1) {
            Intent intent = new Intent(QuestionActivity.this, ResultsActivity.class);
            intent.putExtra("totalRights", totalRights);
            intent.putExtra("totalWrongs", totalWrongs);
            startActivity(intent);
        }
    }
}