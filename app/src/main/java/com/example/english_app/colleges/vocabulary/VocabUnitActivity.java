package com.example.english_app.colleges.vocabulary;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class VocabUnitActivity extends AppCompatActivity implements CheckStarClickInterface {
    ArrayList<String> starV = new ArrayList<>();
    ArrayList<String> starC = new ArrayList<>();
    String table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_elementary);
        RecyclerView rcvVocabulary = findViewById(R.id.elem_rcv_vocabulary);
        VocabularyRcvAdapter vocabularyRcvAdapter = new VocabularyRcvAdapter(getListVocabulary(), this);
        rcvVocabulary.setAdapter(vocabularyRcvAdapter);
        rcvVocabulary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ImageView backBtn = findViewById(R.id.vocBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private ArrayList<VocabularyRcvModel> getListVocabulary() {
        ArrayList<VocabularyRcvModel> list = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        int position = sharedPreferences.getInt("position", 0);
        String units;
        if (position < 9) {
            units = "Unit 0" + (position + 1);
        } else {
            units = "Unit " + (position + 1);
        }

        TextView unitText = findViewById(R.id.elem_unit_text);
        runOnUiThread(() -> (unitText).setText(units));
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try {
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                int titles = sharedPreferences.getInt("title", 0);
                String query = "select Vocabulary, Chinese from voc_elem where Unit = ? ";
                switch (titles) {
                    case 0:
                        query = "select Vocabulary, Chinese from voc_elem where Unit = ? ";
                        table = "voc_elem";
                        break;
                    case 1:
                        query = "select Vocabulary, Chinese from voc_jhs where Unit = ? ";
                        table = "voc_jhs";
                        break;
                    case 2:
                        query = "select Vocabulary, Chinese from voc_shs where Unit = ? ";
                        table = "voc_shs";
                        break;
                    case 3:
                        query = "select Vocabulary, Chinese from voc_toeic where Unit = ? ";
                        table = "voc_toeic";
                        break;
                    case 4:
                        query = "select Vocabulary, Chinese from voc_toefl where Unit = ? ";
                        table = "voc_toefl";
                        break;
                }
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, units);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    list.add(new VocabularyRcvModel(resultSet.getString(1), resultSet.getString(2)));
                    starV.add(resultSet.getString(1));
                    starC.add(resultSet.getString(2));
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

    @Override
    public void onStarClicked(int position) {
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try {
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                String query = "select * from collection where user_phone = ? AND title = ? AND Vocabulary = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, sharedPreferences.getString("user_phone", ""));
                statement.setString(2, table);
                statement.setString(3, starV.get(position));
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Looper.prepare();
                    Toast.makeText(this, "單字已存在收藏清單", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                } else {
                    query = "insert into collection values(?, ?, ?, ?);";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, sharedPreferences.getString("user_phone", ""));
                    statement.setString(2, table);
                    statement.setString(3, starV.get(position));
                    statement.setString(4, starC.get(position));
                    int resultSet1 = statement.executeUpdate();
                    if (resultSet1 != 0) {
                        Looper.prepare();
                        Toast.makeText(this, "已加入收藏", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}