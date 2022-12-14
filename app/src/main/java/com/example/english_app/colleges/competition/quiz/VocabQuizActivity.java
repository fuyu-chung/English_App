package com.example.english_app.colleges.competition.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.english_app.R;
import com.example.english_app.colleges.competition.CompeteActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VocabQuizActivity extends AppCompatActivity {
    private TextView questionText, optionA, optionB, optionC, optionD, qNumber, cNumber;
    int total = 1;
    int score, correct;
    ProgressBar progressBar;
    final int PROGRESS_BAR = 10;
    String Score, Total;
    int question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_test);

        final ImageView checkViewA = findViewById(R.id.checkA);
        final ImageView checkViewB = findViewById(R.id.checkB);
        final ImageView checkViewC = findViewById(R.id.checkC);
        final ImageView checkViewD = findViewById(R.id.checkD);
        final ImageView crossViewA = findViewById(R.id.crossA);
        final ImageView crossViewB = findViewById(R.id.crossB);
        final ImageView crossViewC = findViewById(R.id.crossC);
        final ImageView crossViewD = findViewById(R.id.crossD);

        questionText = findViewById(R.id.question);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        qNumber = findViewById(R.id.QuestionNumber);
        cNumber = findViewById(R.id.score);
        progressBar = findViewById(R.id.progress_bar);
        updateQuestion();

        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogTheme);
        View view = LayoutInflater.from(this).inflate(
                R.layout.check_dialog, findViewById(R.id.layoutCheckDialog)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.dialogTextTitle)).setText("英格利許學校大聲公");
        ((TextView) view.findViewById(R.id.dailogText)).setText("歡迎進入國小單字 - 中翻英關卡");
        ((Button) view.findViewById(R.id.noBtn)).setText("取消");
        ((Button) view.findViewById(R.id.yesBtn)).setText("開始遊戲");
        ((ImageView) view.findViewById(R.id.megaPhoneImg)).setImageResource(R.drawable.ic_megaphone);

        final AlertDialog alertDialog =builder.create();
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
        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

        Total = total + " / 10 Question";
        Score = "Score " + correct + " / 10";
        runOnUiThread(() -> (qNumber).setText(Total));
        runOnUiThread(() -> (cNumber).setText(Score));
        if (total <= 10) {
            optionA.setOnClickListener(v -> checkAnswer(0, question));

            optionB.setOnClickListener(v -> checkAnswer(1, question));

            optionC.setOnClickListener(v -> checkAnswer(2, question));

            optionD.setOnClickListener(v -> checkAnswer(3, question));
        }
    }

    private void updateQuestion() {
        SharedPreferences sharedPreferences = getSharedPreferences("VocabCompetition", MODE_PRIVATE);
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try { //試跑try有問題就跑catch
                int answer;
                int k = 0;
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                String query = "select Chinese, Vocabulary from voc_elem where Orders = ? OR Orders = ? OR Orders = ? OR Orders = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                int[] random_answer = new int[4];
                for (int i = 0; i < 4; i++) {
                    answer = (int) (Math.random() * 552) + 1;
                    random_answer[i] = answer;
                    System.out.println(answer);
                }
                question = (int) (Math.random() * 4);
                System.out.println(random_answer[question]);
                statement.setInt(1, random_answer[0]);
                statement.setInt(2, random_answer[1]);
                statement.setInt(3, random_answer[2]);
                statement.setInt(4, random_answer[3]);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    if (k == question) {
                        sharedPreferences.edit().putString("Q", resultSet.getString(1)).apply();
                        sharedPreferences.edit().putString("Ans", resultSet.getString(2)).apply();
                        runOnUiThread(() -> (questionText).setText(sharedPreferences.getString("Q", "")));
                    }

                    if (k == 0) {
                        sharedPreferences.edit().putString("A", resultSet.getString(2)).apply();
                        runOnUiThread(() -> (optionA).setText(sharedPreferences.getString("A", "")));
                    } else if (k == 1) {
                        sharedPreferences.edit().putString("B", resultSet.getString(2)).apply();
                        runOnUiThread(() -> (optionB).setText(sharedPreferences.getString("B", "")));
                    } else if (k == 2) {
                        sharedPreferences.edit().putString("C", resultSet.getString(2)).apply();
                        runOnUiThread(() -> (optionC).setText(sharedPreferences.getString("C", "")));
                    } else if (k == 3) {
                        sharedPreferences.edit().putString("D", resultSet.getString(2)).apply();
                        runOnUiThread(() -> (optionD).setText(sharedPreferences.getString("D", "")));
                    }
                    k++;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void checkAnswer(int userSelection, int ans) {
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        int temp_score = sharedPreferences.getInt("total", 0);
        if (userSelection == ans) {
            if (total < 10) {
                //要正確勾勾 checkViewABCD.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Right！", Toast.LENGTH_SHORT).show();
                correct++;
                total++;
                score += 50;
                Score = "Score " + correct + " / 10";
                Total = total + " / 10 Question";
                runOnUiThread(() -> (cNumber).setText(Score));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateQuestion();
                runOnUiThread(() -> (qNumber).setText(Total));
                progressBar.setProgress((total - 1) * PROGRESS_BAR);
            } else if (total == 10) {
                //要正確勾勾 checkViewABCD.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Right！", Toast.LENGTH_SHORT).show();
                correct++;
                score += 50;
                Score = "Score " + correct + " / 10";
                Total = total + " / 10 Question";
                runOnUiThread(() -> (cNumber).setText(Score));
                runOnUiThread(() -> (qNumber).setText(Total));
                progressBar.setProgress(100);

                if (correct == 10) {
                    score += 100;
                }
                temp_score += score;
                sharedPreferences.edit().putInt("total", temp_score).apply();
                ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
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

                AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogTheme);
                View view = LayoutInflater.from(this).inflate(
                        R.layout.gameover_dialog, findViewById(R.id.layoutGameOverDialog)
                );
                builder.setView(view);
                ((TextView) view.findViewById(R.id.dialogTextTitle)).setText("GameOver!");
                ((TextView) view.findViewById(R.id.dailogText)).setText("正確題數 : " + correct + " / 10\n" + "成就  + " + score);
                ((Button) view.findViewById(R.id.backComBtn)).setText("返回競賽學院");
                ((ImageView) view.findViewById(R.id.megaPhoneImg)).setImageResource(R.drawable.achievement);

                final AlertDialog alertDialog =builder.create();
                builder.setCancelable(false);
                //取消
                view.findViewById(R.id.backComBtn).setOnClickListener(v -> {
                    this.finish();//退出Quiz Activity
                });

                if(alertDialog.getWindow() != null){
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }

                alertDialog.show();

            }
        } else {
            if (total < 10) {
                //要錯誤 crossViewABCD.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Wrong！", Toast.LENGTH_SHORT).show();
                total++;
                Score = "Score " + correct + " / 10";
                Total = total + " / 10 Question";
                runOnUiThread(() -> (cNumber).setText(Score));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateQuestion();
                runOnUiThread(() -> (qNumber).setText(Total));
                progressBar.setProgress((total - 1) * PROGRESS_BAR);
            } else if (total == 10) {
                Toast.makeText(this, "Wrong！", Toast.LENGTH_SHORT).show();
                Score = "Score " + correct + " / 10";
                Total = total + " / 10 Question";
                runOnUiThread(() -> (cNumber).setText(Score));
                runOnUiThread(() -> (qNumber).setText(Total));
                progressBar.setProgress(100);

                temp_score += score;
                sharedPreferences.edit().putInt("total", temp_score).apply();
                ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
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

//                AlertDialog.Builder alert = new AlertDialog.Builder(this);
//                alert.setTitle("遊戲結束");
//                alert.setCancelable(false);
//                alert.setMessage("正確題數 : " + correct + " / 10\n" + "成就  + " + score);
//                alert.setPositiveButton("返回競賽學院", (dialog, which) -> {
//                    dialog.dismiss();
//                    this.finish();//退出Quiz Activity
//                });

                AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogTheme);
                View view = LayoutInflater.from(this).inflate(
                        R.layout.gameover_dialog, findViewById(R.id.layoutGameOverDialog)
                );
                builder.setView(view);
                ((TextView) view.findViewById(R.id.dialogTextTitle)).setText("GameOver!");
                ((TextView) view.findViewById(R.id.dailogText)).setText("正確題數 : " + correct + " / 10\n" + "成就  + " + score);
                ((Button) view.findViewById(R.id.backComBtn)).setText("返回競賽學院");
                ((ImageView) view.findViewById(R.id.megaPhoneImg)).setImageResource(R.drawable.achievement);

                final AlertDialog alertDialog =builder.create();
                builder.setCancelable(false);
                //取消
                view.findViewById(R.id.backComBtn).setOnClickListener(v -> {
                    this.finish();//退出Quiz Activity
                });

                if(alertDialog.getWindow() != null){
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }

                alertDialog.show();



            }
        }
    }
}