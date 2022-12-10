package com.example.english_app.colleges.vocabulary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.english_app.R;

import java.util.ArrayList;

public class VocabElementaryActivity extends AppCompatActivity {

    private VocabularyRcvAdapter vocabularyRcvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_elementary);

        ArrayList<VocabularyRcvModel> vocabularyList = new ArrayList<>();
        vocabularyList.add(new VocabularyRcvModel("ABC", "就是ABC"));
        vocabularyList.add(new VocabularyRcvModel("ABC", "就是ABC"));
        vocabularyList.add(new VocabularyRcvModel("ABC", "就是ABC"));
        vocabularyList.add(new VocabularyRcvModel("ABC", "就是ABC"));

        RecyclerView rcvVocabulary = findViewById(R.id.elem_rcv_vocabulary);
        VocabularyRcvAdapter vocabularyRcvAdapter = new VocabularyRcvAdapter(vocabularyList);
        rcvVocabulary.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvVocabulary.setAdapter(vocabularyRcvAdapter);
    }
}