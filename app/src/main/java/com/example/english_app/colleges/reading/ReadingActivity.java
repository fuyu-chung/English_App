package com.example.english_app.colleges.reading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.english_app.R;
import com.example.english_app.colleges.vocabulary.VocabularyRcvAdapter;
import com.example.english_app.colleges.vocabulary.VocabularyRcvModel;

import java.util.ArrayList;

public class ReadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        RecyclerView rcvReading = findViewById(R.id.reading_rcv);
        ReadingAdapter readingAdapter = new ReadingAdapter(getListReading());
        rcvReading.setAdapter(readingAdapter);
        rcvReading.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private ArrayList<ReadingModel> getListReading() {
        ArrayList<ReadingModel> list = new ArrayList<>();

        return list;

    }
}