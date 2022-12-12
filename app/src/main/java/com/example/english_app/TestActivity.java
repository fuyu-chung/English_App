package com.example.english_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    private TextView optionA, optionB, optionC, optionD;
    private TextView questionNumber, question, score;
    private TextView checkOut1, checkOut2;
    int currentIndex;
    int mScore = 0;
    int qn = 1;
    ProgressBar progressBar;

    int CurrentQuestion, CurrentOptionA, CurrentOptionB, CurrentOptionC, CurrentOptionD;

    private Answer[] questionBank = new Answer[]{
            new Answer(R.string.question_1, R.string.question1_A, R.string.question1_B, R.string.question1_C, R.string.question1_D, R.string.answer_1),
            new Answer(R.string.question_2, R.string.question_2A, R.string.question_2B, R.string.question_2C, R.string.question_2D, R.string.answer_2),
            new Answer(R.string.question_3, R.string.question_3A, R.string.question_3B, R.string.question_3C, R.string.question_3D, R.string.answer_3),
            new Answer(R.string.question_4, R.string.question_4A, R.string.question_4B, R.string.question_4C, R.string.question_4D, R.string.answer_4),
            new Answer(R.string.question_5, R.string.question_5A, R.string.question_5B, R.string.question_5C, R.string.question_5D, R.string.answer_5),
            new Answer(R.string.question_6, R.string.question_6A, R.string.question_6B, R.string.question_6C, R.string.question_6D, R.string.answer_6),
            new Answer(R.string.question_7, R.string.question_7A, R.string.question_7B, R.string.question_7C, R.string.question_7D, R.string.answer_7),
            new Answer(R.string.question_8, R.string.question_8A, R.string.question_8B, R.string.question_8C, R.string.question_8D, R.string.answer_8),
    };

    final int PROGRESS_BAR = (100 / questionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);

        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        questionNumber = findViewById(R.id.QuestionNumber);

        checkOut1 = findViewById(R.id.select_option);
        checkOut2 = findViewById(R.id.CorrectAnswer);
        progressBar = findViewById(R.id.progress_bar);

        CurrentQuestion = questionBank[currentIndex].getQuestionId();
        question.setText(CurrentQuestion);
        CurrentOptionA = questionBank[currentIndex].getOptionA();
        optionA.setText(CurrentOptionA);
        CurrentOptionB = questionBank[currentIndex].getOptionB();
        optionB.setText(CurrentOptionB);
        CurrentOptionC = questionBank[currentIndex].getOptionC();
        optionC.setText(CurrentOptionC);
        CurrentOptionD = questionBank[currentIndex].getOptionD();
        optionD.setText(CurrentOptionD);



        optionA.setOnClickListener(v -> {
            checkAnswer(CurrentOptionA);
            updateQuestion();

        });

        optionB.setOnClickListener(v -> {
            checkAnswer(CurrentOptionB);
            updateQuestion();
        });
        optionC.setOnClickListener(v -> {
            checkAnswer(CurrentOptionC);
            updateQuestion();

        });
        optionD.setOnClickListener(v -> {
            checkAnswer(CurrentOptionD);
            updateQuestion();

        });
    }

    private void checkAnswer(int userSelection) {
        int correct_answer = questionBank[currentIndex].getAnswerId();
        checkOut1.setText(userSelection);
        checkOut2.setText(correct_answer);

        String m = checkOut1.getText().toString().trim();
        String n = checkOut2.getText().toString().trim();
        if (m.equals(n)) {
            Toast.makeText(getApplicationContext(), "Right", Toast.LENGTH_SHORT).show();
            mScore = mScore + 1;
        } else {
            Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateQuestion() {

        currentIndex = (currentIndex + 1) % questionBank.length;

        if (currentIndex == 0) {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("Your Score" + mScore + "points");
            alert.setPositiveButton("Close Application", (dialog, which) -> {
                finish();
            });

            alert.setNegativeButton("No", (dialog, which) -> {
                mScore = 0;
                qn = 1;
                progressBar.setProgress(0);
                score.setText("Score" + mScore + "/" + questionBank.length);
                questionNumber.setText(qn + "/" + questionBank.length + "Question");
            });
            alert.show();
        }

        CurrentQuestion = questionBank[currentIndex].getQuestionId();
        question.setText(CurrentQuestion);
        CurrentOptionA = questionBank[currentIndex].getOptionA();
        optionA.setText(CurrentOptionA);
        CurrentOptionB = questionBank[currentIndex].getOptionB();
        optionB.setText(CurrentOptionB);
        CurrentOptionC = questionBank[currentIndex].getOptionC();
        optionC.setText(CurrentOptionC);
        CurrentOptionD = questionBank[currentIndex].getOptionD();
        optionD.setText(CurrentOptionD);
        qn = qn + 1;

        if (qn <= questionBank.length) {
            questionNumber.setText(qn + "/" + questionBank.length + "Question");
        }
        score.setText("Score" + mScore + "/" + questionBank.length);
        progressBar.incrementProgressBy(PROGRESS_BAR);
    }
}