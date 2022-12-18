package com.example.english_app.colleges.reading.news_part;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.english_app.R;

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

    @Override
    public void onNewsTitleClicked(int position) {
        //        String url = url_list.get(position);
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        System.out.println("POSITION: "+ position);
//        System.out.println("url: "+ url);
//        startActivity(browserIntent);
    }
}