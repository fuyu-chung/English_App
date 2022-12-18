package com.example.english_app.colleges.reading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.english_app.R;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        ImageView backBtn = findViewById(R.id.readingBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }
}