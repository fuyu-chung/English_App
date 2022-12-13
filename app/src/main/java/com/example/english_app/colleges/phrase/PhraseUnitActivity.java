package com.example.english_app.colleges.phrase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.english_app.R;

import java.util.ArrayList;

public class PhraseUnitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase_unit);

        RecyclerView rcvPhraseUnit = findViewById(R.id.phrase_rcv);
        PhraseRcvAdapter phraseRcvAdapter = new PhraseRcvAdapter(getListPhrase());
        rcvPhraseUnit.setAdapter(phraseRcvAdapter);
        rcvPhraseUnit.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        ImageView backBtn = findViewById(R.id.vocBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private ArrayList<PhraseRcvModel> getListPhrase() {
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        int position = sharedPreferences.getInt("position", 0);

        //unit 標題
        TextView unitText = findViewById(R.id.phrase_unit_text);


        return mlist; //我們自己的
    }
}