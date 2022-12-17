package com.example.english_app.mainpage_fragments.Library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.english_app.R;

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
}