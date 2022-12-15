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

public class VocabQuizActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_vocab_quiz);
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
        ((TextView) view.findViewById(R.id.dailogText)).setText("準備好了嗎?");
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
                checkAnswer(0, question);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        optionB.setOnClickListener(v -> {
            try {
                checkAnswer(1, question);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        optionC.setOnClickListener(v -> {
            try {
                checkAnswer(2, question);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        optionD.setOnClickListener(v -> {
            try {
                checkAnswer(3, question);
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
                int answer;
                int k = 0;
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                String query = "";
                int[] random_answer = new int[4];
                level = sharedPreferences1.getInt("position", 0) + 1;
                System.out.println(level);
                if (level == 1) {
                    query = "select Chinese, Vocabulary from voc_elem where Orders = ? OR Orders = ? OR Orders = ? OR Orders = ?";
                    for (int i = 0; i < 4; i++) {
                        answer = (int) (Math.random() * 552) + 1;
                        random_answer[i] = answer;
                        System.out.println(answer);
                        title = "voc_elem";
                    }
                } else if (level == 2) {
                    query = "select Vocabulary, Chinese from voc_elem where Orders = ? OR Orders = ? OR Orders = ? OR Orders = ?";
                    for (int i = 0; i < 4; i++) {
                        answer = (int) (Math.random() * 552) + 1;
                        random_answer[i] = answer;
                        System.out.println(answer);
                        title = "voc_elem";
                    }
                } else if (level == 3) {
                    query = "select Chinese, Vocabulary from voc_jhs where Orders = ? OR Orders = ? OR Orders = ? OR Orders = ?";
                    for (int i = 0; i < 4; i++) {
                        answer = (int) (Math.random() * 1248) + 1;
                        random_answer[i] = answer;
                        System.out.println(answer);
                        title = "voc_jhs";
                    }
                } else if (level == 4) {
                    query = "select  Vocabulary, Chinese from voc_jhs where Orders = ? OR Orders = ? OR Orders = ? OR Orders = ?";
                    for (int i = 0; i < 4; i++) {
                        answer = (int) (Math.random() * 1248) + 1;
                        random_answer[i] = answer;
                        System.out.println(answer);
                        title = "voc_jhs";
                    }
                } else if (level == 5) {
                    query = "select Chinese, Vocabulary from voc_shs where Orders = ? OR Orders = ? OR Orders = ? OR Orders = ?";
                    for (int i = 0; i < 4; i++) {
                        answer = (int) (Math.random() * 6239) + 1;
                        random_answer[i] = answer;
                        System.out.println(answer);
                        title = "voc_shs";
                    }
                } else if (level == 6) {
                    query = "select  Vocabulary, Chinese from voc_shs where Orders = ? OR Orders = ? OR Orders = ? OR Orders = ?";
                    for (int i = 0; i < 4; i++) {
                        answer = (int) (Math.random() * 6239) + 1;
                        random_answer[i] = answer;
                        System.out.println(answer);
                        title = "voc_shs";
                    }
                } else if (level == 7) {
                    query = "select Chinese, Vocabulary from voc_toeic where Orders = ? OR Orders = ? OR Orders = ? OR Orders = ?";
                    for (int i = 0; i < 4; i++) {
                        answer = (int) (Math.random() * 910) + 1;
                        random_answer[i] = answer;
                        System.out.println(answer);
                        title = "voc_toeic";
                    }
                } else if (level == 8) {
                    query = "select  Vocabulary, Chinese from voc_toeic where Orders = ? OR Orders = ? OR Orders = ? OR Orders = ?";
                    for (int i = 0; i < 4; i++) {
                        answer = (int) (Math.random() * 910) + 1;
                        random_answer[i] = answer;
                        System.out.println(answer);
                        title = "voc_toeic";
                    }
                } else if (level == 9) {
                    query = "select Chinese, Vocabulary from voc_toefl where Orders = ? OR Orders = ? OR Orders = ? OR Orders = ?";
                    for (int i = 0; i < 4; i++) {
                        answer = (int) (Math.random() * 2286) + 1;
                        random_answer[i] = answer;
                        System.out.println(answer);
                        title = "voc_toefl";
                    }
                } else if (level == 10) {
                    query = "select  Vocabulary, Chinese from voc_toefl where Orders = ? OR Orders = ? OR Orders = ? OR Orders = ?";
                    for (int i = 0; i < 4; i++) {
                        answer = (int) (Math.random() * 2286) + 1;
                        random_answer[i] = answer;
                        System.out.println(answer);
                        title = "voc_toefl";
                    }
                }

                PreparedStatement statement = connection.prepareStatement(query);
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

    private void checkAnswer(int userSelection, int ans) throws InterruptedException {
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = getSharedPreferences("VocabCompetition", MODE_PRIVATE);
        int temp_score = sharedPreferences.getInt("total", 0);
        if (userSelection == ans) {
            if (userSelection == 0) {
                checkViewA.setVisibility(View.VISIBLE);
            } else if (userSelection == 1) {
                checkViewB.setVisibility(View.VISIBLE);
            } else if (userSelection == 2) {
                checkViewC.setVisibility(View.VISIBLE);
            } else if (userSelection == 3) {
                checkViewD.setVisibility(View.VISIBLE);
            }
            if (total < 10) {
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
            if (userSelection == 0) {
                crossViewA.setVisibility(View.VISIBLE);
            } else if (userSelection == 1) {
                crossViewB.setVisibility(View.VISIBLE);
            } else if (userSelection == 2) {
                crossViewC.setVisibility(View.VISIBLE);
            } else if (userSelection == 3) {
                crossViewD.setVisibility(View.VISIBLE);
            }

            if (ans == 0) {
                checkViewA.setVisibility(View.VISIBLE);
            } else if (ans == 1) {
                checkViewB.setVisibility(View.VISIBLE);
            } else if (ans == 2) {
                checkViewC.setVisibility(View.VISIBLE);
            } else if (ans == 3) {
                checkViewD.setVisibility(View.VISIBLE);
            }
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
// 建立新的thread
            if (level % 2 == 1) {
                executor.execute(() -> {
                    try {
                        String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                        Connection connection = DriverManager.getConnection(s1); //建立連線
                        String query = "insert into wrong values (?, ?, ?, ?);";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, sharedPreferences.getString("user_phone", ""));
                        statement.setString(2, title);
                        statement.setString(3, sharedPreferences1.getString("Ans", ""));
                        statement.setString(4, sharedPreferences1.getString("Q", ""));
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                executor.execute(() -> {
                    try {
                        String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                        Connection connection = DriverManager.getConnection(s1); //建立連線
                        String query = "insert into wrong values (?, ?, ?, ?);";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, sharedPreferences.getString("user_phone", ""));
                        statement.setString(2, title);
                        statement.setString(3, sharedPreferences1.getString("Q", ""));
                        statement.setString(4, sharedPreferences1.getString("Ans", ""));
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
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