package com.example.english_app.colleges.phrase;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.english_app.R;

public class PhraseUnitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase_unit);

        ImageView backBtn = findViewById(R.id.vocBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }
}