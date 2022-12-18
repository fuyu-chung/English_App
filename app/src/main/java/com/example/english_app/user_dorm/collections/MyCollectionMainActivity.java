package com.example.english_app.user_dorm.collections;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.user_dorm.collections.CollectionInterface.CheckWhatCollectInterface;
import com.example.english_app.user_dorm.collections.CollectionInterface.RecyclerColViewInterface;
import com.example.english_app.user_dorm.collections.CollectionInterface.UpdateCollectionRecyclerView;

import java.util.ArrayList;

public class MyCollectionMainActivity extends AppCompatActivity implements UpdateCollectionRecyclerView, RecyclerColViewInterface,CheckWhatCollectInterface {

    private RecyclerView rcvCollection;
    private CollectionDynamicRcvAdapter cDynamicRcvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection_main);

        ArrayList<CollectionStaticRcvModel> colItem = new ArrayList<>();
        colItem.add(new CollectionStaticRcvModel(R.drawable.house_vocabulary, "單字學院"));
        colItem.add(new CollectionStaticRcvModel(R.drawable.house_phrase, "片語學院"));

        RecyclerView rcvColTitle = findViewById(R.id.coll_rcv_01);
        CollectionStaticRcvAdapter cStaticRcvAdapter = new CollectionStaticRcvAdapter(colItem, this, this, this);
        rcvColTitle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        rcvColTitle.setAdapter(cStaticRcvAdapter);

        //初始顯示
        ArrayList<CollectionDynamicRcvModel> collectItem = new ArrayList<>();
        collectItem.add(new CollectionDynamicRcvModel("國小單字 Collections", "#CEB443"));
        collectItem.add(new CollectionDynamicRcvModel("國中單字 Collections", "#CEB443"));
        collectItem.add(new CollectionDynamicRcvModel("高中單字 Collections", "#CEB443"));
        collectItem.add(new CollectionDynamicRcvModel("多益單字 Collections", "#CEB443"));
        collectItem.add(new CollectionDynamicRcvModel("托福單字 Collections", "#CEB443"));

        rcvCollection = findViewById(R.id.coll_rcv_02);
        cDynamicRcvAdapter = new CollectionDynamicRcvAdapter(collectItem, this);
        rcvCollection.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvCollection.setAdapter(cDynamicRcvAdapter);


    }

    @Override
    public void collcallback(int position, ArrayList<CollectionDynamicRcvModel> items) {
        cDynamicRcvAdapter = new CollectionDynamicRcvAdapter(items, this);
        cDynamicRcvAdapter.notifyDataSetChanged();
        rcvCollection.setAdapter(cDynamicRcvAdapter);
    }


    @Override
    public void onCollectClicked(int position) {
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        sharedPreferences.edit().putInt("title", position).apply();
        //單字的
    }


    @Override
    public void onItemClicked(int position) {
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        sharedPreferences.edit().putInt("position", position).apply();
        Intent intent = new Intent(MyCollectionMainActivity.this, MyCollectionActivity.class);
        startActivity(intent);
    }
}