package com.example.english_app.colleges.competition.quiz;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.english_app.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatchingTenQuizActivity extends AppCompatActivity {
    private TextView questionText;
    private EditText text1, text2, text3, text4, text5, text6, text7, text8, text9, text10;
    private TextView optionA, optionB, optionC, optionD, optionE, optionF, optionG, optionH, optionI, optionJ, optionK, optionL;
    private ImageView checkViewA, checkViewB, checkViewC, checkViewD, checkViewE, checkViewF, checkViewG, checkViewH, checkViewI, checkViewJ;
    private ImageView crossViewA, crossViewB, crossViewC, crossViewD, crossViewE, crossViewF, crossViewG, crossViewH, crossViewI, crossViewJ;
    String answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9, answer10;
    int score, correct;
    int temp_score;
    int question;
    int level;
    String title;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_ten_quiz);
        checkViewA = findViewById(R.id.checkA);
        checkViewB = findViewById(R.id.checkB);
        checkViewC = findViewById(R.id.checkC);
        checkViewD = findViewById(R.id.checkD);
        checkViewE = findViewById(R.id.checkE);
        checkViewF = findViewById(R.id.checkF);
        checkViewG = findViewById(R.id.checkG);
        checkViewH = findViewById(R.id.checkH);
        checkViewI = findViewById(R.id.checkI);
        checkViewJ = findViewById(R.id.checkJ);

        crossViewA = findViewById(R.id.crossA);
        crossViewB = findViewById(R.id.crossB);
        crossViewC = findViewById(R.id.crossC);
        crossViewD = findViewById(R.id.crossD);
        crossViewE = findViewById(R.id.crossE);
        crossViewF = findViewById(R.id.crossF);
        crossViewG = findViewById(R.id.crossG);
        crossViewH = findViewById(R.id.crossH);
        crossViewI = findViewById(R.id.crossI);
        crossViewJ = findViewById(R.id.crossJ);

        questionText = findViewById(R.id.question);
        text1 = findViewById(R.id.text01);
        text2 = findViewById(R.id.text02);
        text3 = findViewById(R.id.text03);
        text4 = findViewById(R.id.text04);
        text5 = findViewById(R.id.text05);
        text6 = findViewById(R.id.text06);
        text7 = findViewById(R.id.text07);
        text8 = findViewById(R.id.text08);
        text9 = findViewById(R.id.text09);
        text10 = findViewById(R.id.text10);

        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        optionE = findViewById(R.id.optionE);
        optionF = findViewById(R.id.optionF);
        optionG = findViewById(R.id.optionG);
        optionH = findViewById(R.id.optionH);
        optionI = findViewById(R.id.optionI);
        optionJ = findViewById(R.id.optionJ);
        optionK = findViewById(R.id.optionK);
        optionL = findViewById(R.id.optionL);

        checkViewA.setVisibility(View.INVISIBLE);
        checkViewB.setVisibility(View.INVISIBLE);
        checkViewC.setVisibility(View.INVISIBLE);
        checkViewD.setVisibility(View.INVISIBLE);
        checkViewE.setVisibility(View.INVISIBLE);
        checkViewF.setVisibility(View.INVISIBLE);
        checkViewG.setVisibility(View.INVISIBLE);
        checkViewH.setVisibility(View.INVISIBLE);
        checkViewI.setVisibility(View.INVISIBLE);
        checkViewJ.setVisibility(View.INVISIBLE);

        crossViewA.setVisibility(View.INVISIBLE);
        crossViewB.setVisibility(View.INVISIBLE);
        crossViewC.setVisibility(View.INVISIBLE);
        crossViewD.setVisibility(View.INVISIBLE);
        crossViewE.setVisibility(View.INVISIBLE);
        crossViewF.setVisibility(View.INVISIBLE);
        crossViewG.setVisibility(View.INVISIBLE);
        crossViewH.setVisibility(View.INVISIBLE);
        crossViewI.setVisibility(View.INVISIBLE);
        crossViewJ.setVisibility(View.INVISIBLE);

        SharedPreferences sharedPreferences = getSharedPreferences("Competition", MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = getSharedPreferences("Position", MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = getSharedPreferences("User", MODE_PRIVATE);

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        View view = LayoutInflater.from(this).inflate(
                R.layout.check_dialog, findViewById(R.id.layoutCheckDialog)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.dialogTextTitle)).setText("英格利許學校大聲公");
        ((TextView) view.findViewById(R.id.dailogText)).setText("文意選填一次十題，準備好了嗎?");
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
            score = 0;
            correct = 0;
            alertDialog.cancel();
        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

        question = (int) (Math.random() * (10) + 1);
        System.out.println(question);
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try { //試跑try有問題就跑catch
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                String query;
                level = sharedPreferences1.getInt("position", 0) + 1;
                System.out.println(level);
                query = "select * from matching where Orders = ?";
                title = "matching";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, question);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    sharedPreferences.edit().putString("Q", resultSet.getString(2)).apply();
                    runOnUiThread(() -> (questionText).setText(sharedPreferences.getString("Q", "")));
                    sharedPreferences.edit().putString("A", resultSet.getString(3)).apply();
                    runOnUiThread(() -> (optionA).setText("(A) " + sharedPreferences.getString("A", "")));
                    sharedPreferences.edit().putString("B", resultSet.getString(4)).apply();
                    runOnUiThread(() -> (optionB).setText("(B) " + sharedPreferences.getString("B", "")));
                    sharedPreferences.edit().putString("C", resultSet.getString(5)).apply();
                    runOnUiThread(() -> (optionC).setText("(C) " + sharedPreferences.getString("C", "")));
                    sharedPreferences.edit().putString("D", resultSet.getString(6)).apply();
                    runOnUiThread(() -> (optionD).setText("(D )" + sharedPreferences.getString("D", "")));
                    sharedPreferences.edit().putString("E", resultSet.getString(7)).apply();
                    runOnUiThread(() -> (optionE).setText("(E) " + sharedPreferences.getString("E", "")));
                    sharedPreferences.edit().putString("F", resultSet.getString(8)).apply();
                    runOnUiThread(() -> (optionF).setText("(F) " + sharedPreferences.getString("F", "")));
                    sharedPreferences.edit().putString("G", resultSet.getString(9)).apply();
                    runOnUiThread(() -> (optionG).setText("(G) " + sharedPreferences.getString("G", "")));
                    sharedPreferences.edit().putString("H", resultSet.getString(10)).apply();
                    runOnUiThread(() -> (optionH).setText("(H) " + sharedPreferences.getString("F", "")));
                    sharedPreferences.edit().putString("I", resultSet.getString(11)).apply();
                    runOnUiThread(() -> (optionI).setText("(I) " + sharedPreferences.getString("I", "")));
                    sharedPreferences.edit().putString("J", resultSet.getString(12)).apply();
                    runOnUiThread(() -> (optionJ).setText("(J) " + sharedPreferences.getString("J", "")));

                    sharedPreferences.edit().putString("Ans1", resultSet.getString(15)).apply();
                    answer1 = sharedPreferences.getString("Ans1", "");
                    sharedPreferences.edit().putString("Ans2", resultSet.getString(16)).apply();
                    answer2 = sharedPreferences.getString("Ans2", "");
                    sharedPreferences.edit().putString("Ans3", resultSet.getString(17)).apply();
                    answer3 = sharedPreferences.getString("Ans3", "");
                    sharedPreferences.edit().putString("Ans4", resultSet.getString(18)).apply();
                    answer4 = sharedPreferences.getString("Ans4", "");
                    sharedPreferences.edit().putString("Ans5", resultSet.getString(19)).apply();
                    answer5 = sharedPreferences.getString("Ans5", "");
                    sharedPreferences.edit().putString("Ans6", resultSet.getString(20)).apply();
                    answer6 = sharedPreferences.getString("Ans6", "");
                    sharedPreferences.edit().putString("Ans7", resultSet.getString(21)).apply();
                    answer7 = sharedPreferences.getString("Ans7", "");
                    sharedPreferences.edit().putString("Ans8", resultSet.getString(22)).apply();
                    answer8 = sharedPreferences.getString("Ans8", "");
                    sharedPreferences.edit().putString("Ans9", resultSet.getString(23)).apply();
                    answer9 = sharedPreferences.getString("Ans9", "");
                    sharedPreferences.edit().putString("Ans10", resultSet.getString(24)).apply();
                    answer10 = sharedPreferences.getString("Ans10", "");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        AppCompatButton sendBtn = findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(v -> {
            if (text1.getText().toString().equals(answer1)) {
                checkViewA.setVisibility(View.VISIBLE);
                correct++;
                score += 80;
            } else {
                crossViewA.setVisibility(View.INVISIBLE);
            }

            if (text2.getText().toString().equals(answer2)) {
                checkViewB.setVisibility(View.VISIBLE);
                correct++;
                score += 80;
            } else {
                crossViewB.setVisibility(View.INVISIBLE);
            }

            if (text3.getText().toString().equals(answer3)) {
                checkViewC.setVisibility(View.VISIBLE);
                correct++;
                score += 80;
            } else {
                crossViewC.setVisibility(View.INVISIBLE);
            }

            if (text4.getText().toString().equals(answer4)) {
                checkViewD.setVisibility(View.VISIBLE);
                correct++;
                score += 80;
            } else {
                crossViewD.setVisibility(View.INVISIBLE);
            }

            if (text5.getText().toString().equals(answer5)) {
                checkViewE.setVisibility(View.VISIBLE);
                correct++;
                score += 80;
            } else {
                crossViewE.setVisibility(View.INVISIBLE);
            }

            if (text6.getText().toString().equals(answer6)) {
                checkViewF.setVisibility(View.VISIBLE);
                correct++;
                score += 80;
            } else {
                crossViewF.setVisibility(View.INVISIBLE);
            }

            if (text7.getText().toString().equals(answer7)) {
                checkViewG.setVisibility(View.VISIBLE);
                correct++;
                score += 80;
            } else {
                crossViewG.setVisibility(View.INVISIBLE);
            }

            if (text8.getText().toString().equals(answer8)) {
                checkViewH.setVisibility(View.VISIBLE);
                correct++;
                score += 80;
            } else {
                crossViewH.setVisibility(View.INVISIBLE);
            }

            if (text9.getText().toString().equals(answer9)) {
                checkViewI.setVisibility(View.VISIBLE);
                correct++;
                score += 80;
            } else {
                crossViewI.setVisibility(View.INVISIBLE);
            }

            if (text10.getText().toString().equals(answer10)) {
                checkViewJ.setVisibility(View.VISIBLE);
                correct++;
                score += 80;
            } else {
                crossViewJ.setVisibility(View.INVISIBLE);
            }

            if (correct == 5) {
                score += 100;
            } else if (correct == 10) {
                score += 200;
            }
        });


        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        temp_score = sharedPreferences2.getInt("total", 0);
        temp_score += score;
        sharedPreferences.edit().putInt("total", temp_score).apply();
        executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try {
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                String query = "update achievement set total = ? where user_phone = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, sharedPreferences.getInt("total", 0));
                statement.setString(2, sharedPreferences.getString("user_phone", ""));
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        AlertDialog.Builder builder2 = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        builder2 = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        view = LayoutInflater.from(this).inflate(
                R.layout.gameover_dialog, findViewById(R.id.layoutGameOverDialog)
        );
        builder2.setView(view);
        ((TextView) view.findViewById(R.id.dialogTextTitle)).setText("GameOver!");
        ((TextView) view.findViewById(R.id.dailogText)).setText("正確題數 : " + correct + " / 10\n" + "成就  + " + score);
        ((Button) view.findViewById(R.id.backComBtn)).setText("返回競賽學院");
        ((ImageView) view.findViewById(R.id.megaPhoneImg)).setImageResource(R.drawable.achievement);

        final AlertDialog alertDialog2 = builder2.create();
        builder2.setCancelable(false);
        //取消
        view.findViewById(R.id.backComBtn).setOnClickListener(v -> {
            this.finish();//退出Quiz Activity
        });

        if (alertDialog2.getWindow() != null) {
            alertDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog2.show();

    }
}


