package com.example.english_app.colleges.reading;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.colleges.reading.news_part.NewsBbcActivity;
import com.example.english_app.colleges.reading.news_part.NewsCnnActivity;
import com.example.english_app.colleges.reading.news_part.NewsHuffActivity;
import com.example.english_app.colleges.reading.news_part.NewsNytActivity;
import com.example.english_app.colleges.reading.news_part.NewsTaiwanActivity;

import java.util.ArrayList;

public class ReadingActivity extends AppCompatActivity implements UpdateNewsRcv, RecyclerReadingViewInterface, CheckWhatNewsInterface{

    private RecyclerView rcvPartItem;
    private ReadingDynamicRcvAdapter readingDynamicRcvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        ArrayList<ReadingStaticRcvModel> newsItem = new ArrayList<>();
        newsItem.add(new ReadingStaticRcvModel(R.drawable.news_twnews, "台灣新聞網"));
        newsItem.add(new ReadingStaticRcvModel(R.drawable.news_nyt, "紐約時報"));
        newsItem.add(new ReadingStaticRcvModel(R.drawable.news_bbc, "BBC"));
        newsItem.add(new ReadingStaticRcvModel(R.drawable.news_cnn, "CNN"));
        newsItem.add(new ReadingStaticRcvModel(R.drawable.news_huff, "哈芬登郵報"));

        RecyclerView rcvNewsTitle = findViewById(R.id.reading_rcv_news);
        ReadingStaticRcvAdapter readingStaticRcvAdapter = new ReadingStaticRcvAdapter(newsItem, this, this, this);
        rcvNewsTitle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcvNewsTitle.setAdapter(readingStaticRcvAdapter);

        ArrayList<ReadingDynamicRcvModel> partItem = new ArrayList<>();
        partItem.add(new ReadingDynamicRcvModel("World", "#3FA0B5"));
        partItem.add(new ReadingDynamicRcvModel("Environment", "#3FA0B5"));
        partItem.add(new ReadingDynamicRcvModel("Society", "#3FA0B5"));
        partItem.add(new ReadingDynamicRcvModel("Politics", "#3FA0B5"));
        partItem.add(new ReadingDynamicRcvModel("Opinion", "#3FA0B5"));
        partItem.add(new ReadingDynamicRcvModel("Food Safety and Health", "#3FA0B5"));
        partItem.add(new ReadingDynamicRcvModel("Sports and Entertainment", "#3FA0B5"));
        partItem.add(new ReadingDynamicRcvModel("Travel and Cuisine", "#3FA0B5"));
        partItem.add(new ReadingDynamicRcvModel("Photo of the day", "#3FA0B5"));
        partItem.add(new ReadingDynamicRcvModel("Others", "#3FA0B5"));


        rcvPartItem = findViewById(R.id.reading_rcv_part);
        readingDynamicRcvAdapter = new ReadingDynamicRcvAdapter(partItem, this);
        rcvPartItem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvPartItem.setAdapter(readingDynamicRcvAdapter);


    }

    @Override
    public void callback(int position, ArrayList<ReadingDynamicRcvModel> items) {
        readingDynamicRcvAdapter = new ReadingDynamicRcvAdapter(items, this);
        readingDynamicRcvAdapter.notifyDataSetChanged();
        rcvPartItem.setAdapter(readingDynamicRcvAdapter);
    }

    @Override
    public void onNewsClicked(int position) {
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        sharedPreferences.edit().putInt("title", position).apply();
    }

    @Override
    public void onPartItemClicked(int position) {
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        sharedPreferences.edit().putInt("position", position).apply();

        int title = sharedPreferences.getInt("title", 0);

        if(title == 0){//Taiwan news
            Intent intent;
            intent = new Intent(this, NewsTaiwanActivity.class);
            startActivity(intent);
        }
        else if(title == 1){//New York Times
            Intent intent;
            intent = new Intent(this, NewsNytActivity.class);
            startActivity(intent);
        }
        else if(title == 2){//BBC
            Intent intent;
            intent = new Intent(this, NewsBbcActivity.class);
            startActivity(intent);
        }
        else if(title == 3){//CNN
            Intent intent;
            intent = new Intent(this, NewsCnnActivity.class);
            startActivity(intent);
        }
        else if(title == 4){//HUFF
            Intent intent;
            intent = new Intent(this, NewsHuffActivity.class);
            startActivity(intent);
        }


    }


}