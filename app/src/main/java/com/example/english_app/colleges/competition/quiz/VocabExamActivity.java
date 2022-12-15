package com.example.english_app.colleges.competition.quiz;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VocabExamActivity extends AppCompatActivity {
    private TextView questionText, optionA, optionB, optionC, optionD, qNumber, cNumber;
    private ImageView checkViewA, checkViewB, checkViewC, checkViewD, crossViewA, crossViewB, crossViewC, crossViewD;
    int total = 1;
    int score, correct;
    ProgressBar progressBar;
    final int PROGRESS_BAR = 10;
    String Score, Total;
    int random_question;
    String answer;
    String title;
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_exam);
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
        updateQuestion();

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        View view = LayoutInflater.from(this).inflate(
                R.layout.check_dialog, findViewById(R.id.layoutCheckDialog)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.dialogTextTitle)).setText("英格利許學校大聲公");
        ((TextView) view.findViewById(R.id.dailogText)).setText("準備好了嗎??");
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

        Total = total + " / 10 Question";
        Score = "Score " + correct + " / 10";
        runOnUiThread(() -> (qNumber).setText(Total));
        runOnUiThread(() -> (cNumber).setText(Score));
        optionA.setOnClickListener(v -> {
            try {
                checkAnswer("A", answer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        optionB.setOnClickListener(v -> {
            try {
                checkAnswer("B", answer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        optionC.setOnClickListener(v -> {
            try {
                checkAnswer("C", answer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        optionD.setOnClickListener(v -> {
            try {
                checkAnswer("D", answer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void updateQuestion() {
        SharedPreferences sharedPreferences = getSharedPreferences("VocabCompetition", MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = getSharedPreferences("Position", MODE_PRIVATE);
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try { //試跑try有問題就跑catch
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                String query = "";
                level = sharedPreferences1.getInt("position", 0) + 1;
                System.out.println(level);

                if (level == 11) {
                    query = "select Question, optionA, optionB, optionC, optionD, Answer from voc_gsat where Orders = ?";
                    random_question = (int) (Math.random() * 145) + 1;
                    System.out.println(random_question);
                    title = "voc_gsat";
                } else if (level == 12) {
                    query = "select Question, optionA, optionB, optionC, optionD, Answer from voc_ast where Orders = ?";
                    random_question = (int) (Math.random() * 160) + 1;
                    System.out.println(random_question);
                    title = "voc_ast";
                }

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, random_question);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    sharedPreferences.edit().putString("Q", resultSet.getString(1)).apply();
                    sharedPreferences.edit().putString("A", resultSet.getString(2)).apply();
                    sharedPreferences.edit().putString("B", resultSet.getString(3)).apply();
                    sharedPreferences.edit().putString("C", resultSet.getString(4)).apply();
                    sharedPreferences.edit().putString("D", resultSet.getString(5)).apply();
                    sharedPreferences.edit().putString("Ans", resultSet.getString(6)).apply();
                    answer = sharedPreferences.getString("Ans", "");
                    runOnUiThread(() -> (questionText).setText(sharedPreferences.getString("Q", "")));
                    runOnUiThread(() -> (optionA).setText(sharedPreferences.getString("A", "")));
                    runOnUiThread(() -> (optionB).setText(sharedPreferences.getString("B", "")));
                    runOnUiThread(() -> (optionC).setText(sharedPreferences.getString("C", "")));
                    runOnUiThread(() -> (optionD).setText(sharedPreferences.getString("D", "")));
                }
                checkViewA.setVisibility(View.INVISIBLE);
                checkViewB.setVisibility(View.INVISIBLE);
                checkViewC.setVisibility(View.INVISIBLE);
                checkViewD.setVisibility(View.INVISIBLE);
                crossViewA.setVisibility(View.INVISIBLE);
                crossViewB.setVisibility(View.INVISIBLE);
                crossViewC.setVisibility(View.INVISIBLE);
                crossViewD.setVisibility(View.INVISIBLE);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void checkAnswer(String userSelection, String answer) throws InterruptedException {
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = getSharedPreferences("VocabCompetition", MODE_PRIVATE);
        int temp_score = sharedPreferences.getInt("total", 0);
        if (userSelection.equals(answer)) {
            switch (userSelection) {
                case "A":
                    checkViewA.setVisibility(View.VISIBLE);
                    break;
                case "B":
                    checkViewB.setVisibility(View.VISIBLE);
                    break;
                case "C":
                    checkViewC.setVisibility(View.VISIBLE);
                    break;
                case "D":
                    checkViewD.setVisibility(View.VISIBLE);
                    break;
            }
            if (total < 10) {
                //要正確勾勾 checkViewABCD.setVisibility(View.VISIBLE);
                //Toast.makeText(this, "Right！", Toast.LENGTH_SHORT).show();
                correct++;
                total++;
                score += 50;
                Score = "Score " + correct + " / 10";
                Total = total + " / 10 Question";
                runOnUiThread(() -> (cNumber).setText(Score));
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                updateQuestion();
                runOnUiThread(() -> (qNumber).setText(Total));
                progressBar.setProgress((total - 1) * PROGRESS_BAR);
            } else if (total == 10) {
                //要正確勾勾 checkViewABCD.setVisibility(View.VISIBLE);
                //Toast.makeText(this, "Right！", Toast.LENGTH_SHORT).show();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
                View view = LayoutInflater.from(this).inflate(
                        R.layout.gameover_dialog, findViewById(R.id.layoutGameOverDialog)
                );
                builder.setView(view);
                ((TextView) view.findViewById(R.id.dialogTextTitle)).setText("GameOver!");
                ((TextView) view.findViewById(R.id.dailogText)).setText("正確題數 : " + correct + " / 10\n" + "成就  + " + score);
                ((Button) view.findViewById(R.id.backComBtn)).setText("返回競賽學院");
                ((ImageView) view.findViewById(R.id.megaPhoneImg)).setImageResource(R.drawable.achievement);

                final AlertDialog alertDialog = builder.create();
                builder.setCancelable(false);
                //取消
                view.findViewById(R.id.backComBtn).setOnClickListener(v -> {
                    this.finish();//退出Quiz Activity
                });

                if (alertDialog.getWindow() != null) {
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();

            }
        } else {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
// 建立新的thread
            if (level % 2 == 1) {
                executor.execute(() -> {
                    try {
                        String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                        Connection connection = DriverManager.getConnection(s1); //建立連線
                        String query = "select * from wrong where user_phone = ? AND Title = ? AND Vocabulary = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, sharedPreferences.getString("user_phone", ""));
                        statement.setString(2, title);
                        statement.setString(3, sharedPreferences1.getString("Ans", ""));
                        ResultSet resultSet = statement.executeQuery();
                        if (!resultSet.next()) {
                            query = "insert into wrong values (?, ?, ?, ?);";
                            statement = connection.prepareStatement(query);
                            statement.setString(1, sharedPreferences.getString("user_phone", ""));
                            statement.setString(2, title);
                            statement.setString(3, sharedPreferences1.getString("Ans", ""));
                            statement.setString(4, sharedPreferences1.getString("Q", ""));
                            statement.executeUpdate();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                executor.execute(() -> {
                    try {
                        String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                        Connection connection = DriverManager.getConnection(s1); //建立連線
                        String query = "select * from wrong where user_phone = ? AND Title = ? AND Vocabulary = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, sharedPreferences.getString("user_phone", ""));
                        statement.setString(2, title);
                        statement.setString(3, sharedPreferences1.getString("Q", ""));
                        ResultSet resultSet = statement.executeQuery();
                        if (!resultSet.next()) {
                            query = "insert into wrong values (?, ?, ?, ?);";
                            statement = connection.prepareStatement(query);
                            statement.setString(1, sharedPreferences.getString("user_phone", ""));
                            statement.setString(2, title);
                            statement.setString(3, sharedPreferences1.getString("Q", ""));
                            statement.setString(4, sharedPreferences1.getString("Ans", ""));
                            statement.executeUpdate();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }

            switch (userSelection) {
                case "A":
                    crossViewA.setVisibility(View.VISIBLE);
                    break;
                case "B":
                    crossViewB.setVisibility(View.VISIBLE);
                    break;
                case "C":
                    crossViewC.setVisibility(View.VISIBLE);
                    break;
                case "D":
                    crossViewD.setVisibility(View.VISIBLE);
                    break;
            }

            switch (answer) {
                case "A":
                    checkViewA.setVisibility(View.VISIBLE);
                    break;
                case "B":
                    checkViewB.setVisibility(View.VISIBLE);
                    break;
                case "C":
                    checkViewC.setVisibility(View.VISIBLE);
                    break;
                case "D":
                    checkViewD.setVisibility(View.VISIBLE);
                    break;
            }
            if (total < 10) {
                total++;
                Score = "Score " + correct + " / 10";
                Total = total + " / 10 Question";
                runOnUiThread(() -> (cNumber).setText(Score));
                try {
                    TimeUnit.MICROSECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                updateQuestion();
                runOnUiThread(() -> (qNumber).setText(Total));
                progressBar.setProgress((total - 1) * PROGRESS_BAR);
            } else if (total == 10) {
                Score = "Score " + correct + " / 10";
                Total = total + " / 10 Question";
                runOnUiThread(() -> (cNumber).setText(Score));
                runOnUiThread(() -> (qNumber).setText(Total));
                progressBar.setProgress(100);

                temp_score += score;
                sharedPreferences.edit().putInt("total", temp_score).apply();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
                View view = LayoutInflater.from(this).inflate(
                        R.layout.gameover_dialog, findViewById(R.id.layoutGameOverDialog)
                );
                builder.setView(view);
                ((TextView) view.findViewById(R.id.dialogTextTitle)).setText("GameOver!");
                ((TextView) view.findViewById(R.id.dailogText)).setText("正確題數 : " + correct + " / 10\n" + "成就  + " + score);
                ((Button) view.findViewById(R.id.backComBtn)).setText("返回競賽學院");
                ((ImageView) view.findViewById(R.id.megaPhoneImg)).setImageResource(R.drawable.achievement);

                final AlertDialog alertDialog = builder.create();
                builder.setCancelable(false);
                //取消
                view.findViewById(R.id.backComBtn).setOnClickListener(v -> {
                    this.finish();//退出Quiz Activity
                });

                if (alertDialog.getWindow() != null) {
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();
            }
        }
    }
}