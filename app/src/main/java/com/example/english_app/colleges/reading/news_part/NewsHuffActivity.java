package com.example.english_app.colleges.reading.news_part;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

public class NewsHuffActivity extends AppCompatActivity  implements CheckWhatNewsClickedInterface {
    ArrayList<String> url_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_huff);

        RecyclerView rcvVocabulary = findViewById(R.id.reading_rcv_news_part);
        NewsAdapter newsAdapter = new NewsAdapter(getListNews(),this);
        rcvVocabulary.setAdapter(newsAdapter);
        rcvVocabulary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        ImageView backBtn = findViewById(R.id.readingBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private ArrayList<NewsModel> getListNews() {
        ArrayList<NewsModel> list = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        int position = sharedPreferences.getInt("position", 0) + 1;
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try {
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                String query = "select title, date from news order by date desc ";
                switch (position) {
                    case 1:
                        query = "select Title, Date, Url from news_HUFFPOST where Orders <= 1000 AND Orders >= 1 order by Orders desc";
                        break;
                    case 2:
                        query = "select Title, Date, Url from news_HUFFPOST where Orders <= 2000 AND Orders >= 1001 order by Orders desc";
                        break;
                    case 3:
                        query = "select Title, Date, Url from news_HUFFPOST where Orders <= 3000 AND Orders >= 2001 order by Orders desc";
                        break;
                    case 4:
                        query = "select Title, Date, Url from news_HUFFPOST where Orders <= 4000 AND Orders >= 3001 order by Orders desc";
                        break;
                    case 5:
                        query = "select Title, Date, Url from news_HUFFPOST where Orders <= 5000 AND Orders >= 4001 order by Orders desc";
                        break;
                    case 6:
                        query = "select Title, Date, Url from news_HUFFPOST where Orders <= 6000 AND Orders >= 5001 order by Orders desc";
                        break;
                    case 7:
                        query = "select Title, Date, Url from news_HUFFPOST where Orders <= 7000 AND Orders >= 6001 order by Orders desc";
                        break;
                    case 8:
                        query = "select Title, Date, Url from news_HUFFPOST where Orders <= 8000 AND Orders >= 7001 order by Orders desc";
                        break;
                    case 9:
                        query = "select Title, Date, Url from news_HUFFPOST where Orders <= 9000 AND Orders >= 8001 order by Orders desc";
                        break;
                    case 10:
                        query = "select Title, Date, Url from news_HUFFPOST where Orders <= 10000 AND Orders >= 9001 order by Orders desc";
                        break;

                    case 11:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 11000 AND Orders >= 10001 order by Orders desc";
                        break;
                    case 12:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 12000 AND Orders >= 11001 order by Orders desc";
                        break;
                    case 13:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 13000 AND Orders >= 12001 order by Orders desc";
                        break;
                    case 14:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 14000 AND Orders >= 13001 order by Orders desc";
                        break;
                    case 15:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 15000 AND Orders >= 14001 order by Orders desc";
                        break;
                    case 16:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 16000 AND Orders >= 15001 order by Orders desc";
                        break;
                    case 17:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 17000 AND Orders >= 16001 order by Orders desc";
                        break;
                    case 18:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 18000 AND Orders >= 17001 order by Orders desc";
                        break;
                    case 19:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 19000 AND Orders >= 18001 order by Orders desc";
                        break;
                    case 20:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 20000 AND Orders >= 19001 order by Orders desc";
                        break;
                    case 21:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 11000 AND Orders >= 10001 order by Orders desc";
                        break;
                    case 22:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 12000 AND Orders >= 11001 order by Orders desc";
                        break;
                    case 23:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 13000 AND Orders >= 12001 order by Orders desc";
                        break;
                    case 24:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 14000 AND Orders >= 13001 order by Orders desc";
                        break;
                    case 25:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 15000 AND Orders >= 14001 order by Orders desc";
                        break;
                    case 26:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 16000 AND Orders >= 15001 order by Orders desc";
                        break;
                    case 27:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 17000 AND Orders >= 16001 order by Orders desc";
                        break;
                    case 28:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 18000 AND Orders >= 17001 order by Orders desc";
                        break;
                    case 29:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 19000 AND Orders >= 18001 order by Orders desc";
                        break;
                    case 30:
                        query = "select Title, Date, Url from news_HUFFPOST_2 where Orders <= 20000 AND Orders >= 19001 order by Orders desc";
                        break;
                }

                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    list.add(new NewsModel(resultSet.getString(1), resultSet.getString(2)));
                    url_list.add(resultSet.getString(3));
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
        String url = url_list.get(position);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        System.out.println("POSITION: "+ position);
        System.out.println("url: "+ url);
        startActivity(browserIntent);
    }
}