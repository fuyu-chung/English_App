package com.example.english_app.user_dorm.collections;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.english_app.R;

public class MyCollectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);




        ImageView backBtn = findViewById(R.id.collBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }
}
//嗨