package com.example.english_app.colleges.vocabulary;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.colleges.vocabulary.DRcvInterface.UpdateRecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VocabElementaryActivity extends AppCompatActivity {

    UpdateRecyclerView updateRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_elementary);

        RecyclerView rcvVocabulary = findViewById(R.id.elem_rcv_vocabulary);
        VocabularyRcvAdapter vocabularyRcvAdapter = new VocabularyRcvAdapter(getListVocabulary());
        rcvVocabulary.setAdapter(vocabularyRcvAdapter);
        rcvVocabulary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private ArrayList<VocabularyRcvModel> getListVocabulary() {
        ArrayList<VocabularyRcvModel> list = new ArrayList<>();

        SharedPreferences sharedPreferencesTitle = getSharedPreferences("Position", MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);

        String title = sharedPreferencesTitle.getString("title","elem_voc");
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
                String query = "select Vocabulary, Chinese from elem_voc where Unit = ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, units);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    list.add(new VocabularyRcvModel(resultSet.getString(1), resultSet.getString(2)));
                }
                executor.shutdown();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        try {
            boolean e = executor.awaitTermination(10, TimeUnit.SECONDS); // await會有錯誤
            if (!e) {
                System.out.println("time out");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    private String getWhichTitle() {
        return "elementary";
    }
}