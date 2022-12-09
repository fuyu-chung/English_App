package com.example.english_app.colleges.vocab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.english_app.R;

import java.util.ArrayList;

public class VocabActivity extends AppCompatActivity {

    private RecyclerView rcvVocTitle;
    private StaticRcvAdapter staticRcvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);

        ArrayList<StaticRcvModel> catItem = new ArrayList<StaticRcvModel>();
        catItem.add(new StaticRcvModel(R.drawable.ic_elem, "國小"));
        catItem.add(new StaticRcvModel(R.drawable.ic_jhs, "國中"));
        catItem.add(new StaticRcvModel(R.drawable.ic_hs, "高中"));
        catItem.add(new StaticRcvModel(R.drawable.ic_toeic, "多益"));
        catItem.add(new StaticRcvModel(R.drawable.ic_toefl, "托福"));

        rcvVocTitle = findViewById(R.id.voc_rcv_cat);
        staticRcvAdapter = new StaticRcvAdapter(catItem);
        rcvVocTitle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rcvVocTitle.setAdapter(staticRcvAdapter);
    }
}