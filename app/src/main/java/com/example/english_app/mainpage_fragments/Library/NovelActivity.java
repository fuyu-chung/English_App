package com.example.english_app.mainpage_fragments.Library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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

public class NovelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_latest);


        RecyclerView rcvNovel = findViewById(R.id.novel_rcv);
        NovelRcvAdapter novelRcvAdapter = new NovelRcvAdapter(getListNovcel());
        rcvNovel.setAdapter(novelRcvAdapter);
        rcvNovel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        ImageView backBtn = findViewById(R.id.novBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private ArrayList<NovelRcvModel> getListNovcel() {
        ArrayList<NovelRcvModel> list = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        int position = sharedPreferences.getInt("position", 0);

        String units;
        if (position < 9) {
            units = "Unit 0" + (position + 1);
        } else {
            units = "Unit " + (position + 1);
        }

        //TODO 改category 上面小標，不是unit
        TextView categoryText = findViewById(R.id.novel_cat_text);
        runOnUiThread(() -> (categoryText).setText(units));

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
                        break;
                    case 1:
                        query = "select Vocabulary, Chinese from voc_jhs where Unit = ? ";
                        break;
                    case 2:
                        query = "select Vocabulary, Chinese from voc_shs where Unit = ? ";
                        break;
                    case 3:
                        query = "select Vocabulary, Chinese from voc_toeic where Unit = ? ";
                        break;
                    case 4:
                        query = "select Vocabulary, Chinese from voc_toefl where Unit = ? ";
                        break;
                }
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, units);
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