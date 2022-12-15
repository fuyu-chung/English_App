package com.example.english_app.user_dorm.wrong;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WrongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);

        RecyclerView rcvWrong = findViewById(R.id.wrong_rcv);
        WrongRcvAdapter wrongRcvAdapter = new WrongRcvAdapter(getListWrong());
        rcvWrong.setAdapter(wrongRcvAdapter);
        rcvWrong.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ImageView backBtn = findViewById(R.id.wrongBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private ArrayList<WrongRcvModel> getListWrong() {
        ArrayList<WrongRcvModel> list = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        int position = sharedPreferences.getInt("position", 0) + 1;
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try {
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                String query = "";
                if (position == 1) {
                    query = "select Vocabulary, Chinese from wrong where Title = 'voc_elem'";
                } else if (position == 2) {
                    query = "select Vocabulary, Chinese from wrong where Title = 'voc_jhs'";
                } else if (position == 3) {
                    query = "select Vocabulary, Chinese from wrong where Title = 'voc_shs'";
                } else if (position == 4) {
                    query = "select Vocabulary, Chinese from wrong where Title = 'voc_toeic'";
                } else if (position == 5) {
                    query = "select Vocabulary, Chinese from wrong where Title = 'voc_toefl'";
                } else if (position == 6) {
                    query = "select Vocabulary, Chinese from wrong where Title = 'voc_gsat'";
                } else if (position == 7) {
                    query = "select Vocabulary, Chinese from wrong where Title = 'voc_ast'";
                }
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    list.add(new WrongRcvModel(resultSet.getString(1), resultSet.getString(2)));
                }
                executor.shutdown();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        try {
            boolean e = executor.awaitTermination(20, TimeUnit.SECONDS); // await會有錯誤
            if (!e) {
                System.out.println("time out");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
}