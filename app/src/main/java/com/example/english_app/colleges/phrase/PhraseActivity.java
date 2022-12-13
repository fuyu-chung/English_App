package com.example.english_app.colleges.phrase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.colleges.phrase.phraseInterface.CheckWhatPhraseInterface;
import com.example.english_app.colleges.phrase.phraseInterface.RecyclerPhraseViewInterface;
import com.example.english_app.colleges.phrase.phraseInterface.UpdatePhraseRecyclerView;

import java.util.ArrayList;

public class PhraseActivity extends AppCompatActivity implements UpdatePhraseRecyclerView, RecyclerPhraseViewInterface, CheckWhatPhraseInterface {

    private RecyclerView rcvPhraseUnit;
    private PhraseDynamicRcvAdapter phraseDynamicRcvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase);

        ArrayList<PhraseStaticRcvModel> catItem = new ArrayList<>();
        catItem.add(new PhraseStaticRcvModel(R.drawable.ic_all, "全部", "12單元"));
        catItem.add(new PhraseStaticRcvModel(R.drawable.ic_gsat, "學測", "4單元"));
        catItem.add(new PhraseStaticRcvModel(R.drawable.ic_ast, "指考", "3單元"));

        RecyclerView recyclerView = findViewById(R.id.phrase_rcv_cat);
        PhraseStaticRcvAdapter phraseStaticRcvAdapter = new PhraseStaticRcvAdapter(catItem, this, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(phraseStaticRcvAdapter);

        ArrayList<PhraseDynamicRcvModel> phraseItem = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String units;
            if (i <= 9) {
                units = "Unit 0" + i;
                phraseItem.add(new PhraseDynamicRcvModel(units, "#CEB443"));
            } else {
                units = "Unit " + i;
                phraseItem.add(new PhraseDynamicRcvModel(units, "#CEB443"));
            }
        }

        rcvPhraseUnit = findViewById(R.id.phrase_rcv_unit);
        phraseDynamicRcvAdapter = new PhraseDynamicRcvAdapter(phraseItem, this);
        rcvPhraseUnit.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvPhraseUnit.setAdapter(phraseDynamicRcvAdapter);
    }

    @Override
    public void callback(int position, ArrayList<PhraseDynamicRcvModel> items) {
        phraseDynamicRcvAdapter = new PhraseDynamicRcvAdapter(items,this);
        phraseDynamicRcvAdapter.notifyDataSetChanged();
        rcvPhraseUnit.setAdapter(phraseDynamicRcvAdapter);
    }

    @Override
    public void onClicked(int position) {
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        sharedPreferences.edit().putInt("title", position).apply();
    }

    @Override
    public void onItemClicked(int position) {
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        sharedPreferences.edit().putInt("position", position).apply();
        Intent intent = new Intent(PhraseActivity.this, PhraseUnitActivity.class);
        startActivity(intent);
    }


}