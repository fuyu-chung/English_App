package com.example.english_app.colleges.competition.quiz;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.english_app.R;

public class ClozeQuizActivity extends AppCompatActivity {

    private TextView questionText, optionA, optionB, optionC, optionD, qNumber, cNumber;
    private ImageView checkViewA, checkViewB, checkViewC, checkViewD, crossViewA, crossViewB, crossViewC, crossViewD;
    int total = 1;
    int score, correct;
    ProgressBar progressBar;
    final int PROGRESS_BAR = 10;
    String Score, Total;
    int question;
    int level;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloze_quiz);

        checkViewA = findViewById(R.id.checkA);
        checkViewB = findViewById(R.id.checkB);
        checkViewC = findViewById(R.id.checkC);
        checkViewD = findViewById(R.id.checkD);
        crossViewA = findViewById(R.id.crossA);
        crossViewB = findViewById(R.id.crossB);
        crossViewC = findViewById(R.id.crossC);
        crossViewD = findViewById(R.id.crossD);

        questionText = findViewById(R.id.question);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        qNumber = findViewById(R.id.QuestionNumber);
        cNumber = findViewById(R.id.score);
        progressBar = findViewById(R.id.progress_bar);

        //TODO 鍾鍾鍾鍾鍾鍾鍾鍾
        //updateQuestion();

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        View view = LayoutInflater.from(this).inflate(
                R.layout.check_dialog, findViewById(R.id.layoutCheckDialog)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.dialogTextTitle)).setText("英格利許學校大聲公");
        ((TextView) view.findViewById(R.id.dailogText)).setText("克漏字題組一次五題，準備好了嗎?");
        ((Button) view.findViewById(R.id.noBtn)).setText("取消");
        ((Button) view.findViewById(R.id.yesBtn)).setText("開始遊戲");
        ((ImageView) view.findViewById(R.id.megaPhoneImg)).setImageResource(R.drawable.ic_megaphone);

        final AlertDialog alertDialog = builder.create();
        builder.setCancelable(false);
        //取消
        view.findViewById(R.id.noBtn).setOnClickListener(v -> {
            this.finish();//退出Quiz Activity
        });

        view.findViewById(R.id.yesBtn).setOnClickListener(v -> {
            total = 1;
            score = 0;
            correct = 0;
            progressBar.setProgress(0);
            alertDialog.cancel();
        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

        Total = total + " / 5 Question";
        Score = "Score " + correct + " / 5";
        runOnUiThread(() -> (qNumber).setText(Total));
        runOnUiThread(() -> (cNumber).setText(Score));

//        optionA.setOnClickListener(v -> {
//            try {
//                //checkAnswer(0, question);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

//        optionB.setOnClickListener(v -> {
//            try {
//                checkAnswer(1, question);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

//        optionC.setOnClickListener(v -> {
//            try {
//                checkAnswer(2, question);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        optionD.setOnClickListener(v -> {
//            try {
//                checkAnswer(3, question);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });




    }
}