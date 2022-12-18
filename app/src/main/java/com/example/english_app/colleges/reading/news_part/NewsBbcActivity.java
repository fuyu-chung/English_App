package com.example.english_app.colleges.reading.news_part;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

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

public class NewsBbcActivity extends AppCompatActivity  implements CheckWhatNewsClickedInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_bbc);

        RecyclerView rcvVocabulary = findViewById(R.id.reading_rcv_news_part);
        NewsAdapter newsAdapter = new NewsAdapter(getListNews(),this);
        rcvVocabulary.setAdapter(newsAdapter);
        rcvVocabulary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        ImageView backBtn = findViewById(R.id.readingBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private ArrayList<NewsModel> getListNews() {
        ArrayList<NewsModel> list = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try {
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                String query = "select title, date from news order by date desc ";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    list.add(new NewsModel(resultSet.getString(1), resultSet.getString(2)));
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

    @Override
    public void onNewsTitleClicked(int position) {
        //        String url = url_list.get(position);
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        System.out.println("POSITION: "+ position);
//        System.out.println("url: "+ url);
//        startActivity(browserIntent);
    }
}