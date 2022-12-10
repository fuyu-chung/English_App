package com.example.english_app.colleges.vocabulary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.english_app.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VocabElementaryActivity extends AppCompatActivity {

    private VocabularyRcvAdapter vocabularyRcvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_elementary);

        //非常爛的傳變數方式
//        String vocabularyTopTitle = "UNIT" + getAb
////
////        TextView unitText = findViewById(R.id.elem_unit_text);
////
////        unitText.setText(vocabularyTopTitle);



        //要改變的單字顯示區(To鍾後端連線)
//        ArrayList<VocabularyRcvModel> vocabularyList = new ArrayList<>();
//
//        vocabularyList.add(new VocabularyRcvModel("ABC", "就是ABC"));
//        vocabularyList.add(new VocabularyRcvModel("ABC", "就是ABC"));
//        vocabularyList.add(new VocabularyRcvModel("ABC", "就是ABC"));
//        vocabularyList.add(new VocabularyRcvModel("ABC", "就是ABC"));
//
//        RecyclerView rcvVocabulary = findViewById(R.id.elem_rcv_vocabulary);
//        VocabularyRcvAdapter vocabularyRcvAdapter = new VocabularyRcvAdapter(vocabularyList);
//        rcvVocabulary.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        rcvVocabulary.setAdapter(vocabularyRcvAdapter);
    }
    private List<VocabularyRcvModel> getListVocabulary(int start, int count) {
        List<VocabularyRcvModel> list = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try {
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                String query = "select Vocabulary, Chinese from elem_voc where Orders = ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                for (int i = 0; i < count; i++){
                    statement.setInt(1, start + i);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        list.add(new VocabularyRcvModel(resultSet.getString(1), resultSet.getString(2)));
                    }
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

    private String getWhichTitle(){
        return "elementary";
    }
}