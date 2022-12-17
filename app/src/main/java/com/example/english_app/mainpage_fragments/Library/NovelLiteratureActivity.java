package com.example.english_app.mainpage_fragments.Library;

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

public class NovelLiteratureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_literature);

        RecyclerView rcvNovel = findViewById(R.id.novel_rcv);
        NovelRcvAdapter novelRcvAdapter = new NovelRcvAdapter(getListNovel());
        rcvNovel.setAdapter(novelRcvAdapter);
        rcvNovel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ImageView backBtn = findViewById(R.id.novBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private ArrayList<NovelRcvModel> getListNovel() {
        ArrayList<NovelRcvModel> list = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try {
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                String query = "select Title, Author from novel where Category = 'Literature'";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    list.add(new NovelRcvModel(resultSet.getString(1), resultSet.getString(2)));
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