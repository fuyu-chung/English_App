package com.example.english_app.user_dorm.collections;

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

public class MyCollectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);

        RecyclerView rcvColl = findViewById(R.id.collection_rcv);
        CollectionRcvAdapter collectionRcvAdapter = new CollectionRcvAdapter(getListCollection());
        rcvColl.setAdapter(collectionRcvAdapter);
        rcvColl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        ImageView backBtn = findViewById(R.id.collBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private ArrayList<CollectionRcvModel> getListCollection() {
        ArrayList<CollectionRcvModel> list = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try {
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                int position = sharedPreferences.getInt("position", 0);
                int title = sharedPreferences.getInt("title", 0);
                System.out.println(position);
                System.out.println(title);
                String query = "";
                if (title == 0) {
                    switch (position) {
                        case 0:
                            query = "select Vocabulary, Chinese from collection where title = 'voc_elem'";
                            break;
                        case 1:
                            query = "select Vocabulary, Chinese from collection where title = 'voc_jhs'";
                            break;
                        case 2:
                            query = "select Vocabulary, Chinese from collection where title = 'voc_shs'";
                            break;
                        case 3:
                            query = "select Vocabulary, Chinese from collection where title = 'voc_toeic'";
                            break;
                        case 4:
                            query = "select Vocabulary, Chinese from collection where title = 'voc_toefl'";
                            break;
                    }
                } else if (title == 1) {
                    switch (position) {
                        case 0:
                            query = "select Vocabulary, Chinese from collection where title = 'phrase_all'";
                            break;
                        case 1:
                            query = "select Vocabulary, Chinese from collection where title = 'phrase_gsat'";
                            break;
                        case 2:
                            query = "select Vocabulary, Chinese from collection where title = 'phrase_ast'";
                            break;
                    }
                }

                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    list.add(new CollectionRcvModel(resultSet.getString(1), resultSet.getString(2)));
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