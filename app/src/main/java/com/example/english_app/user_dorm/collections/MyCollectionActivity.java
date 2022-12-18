package com.example.english_app.user_dorm.collections;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.english_app.R;

import java.util.ArrayList;

public class MyCollectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);

        RecyclerView rcvColl = findViewById(R.id.collection_rcv);
        CollectionRcvAdapter collectionRcvAdapter = new CollectionRcvAdapter(getListCollection());
        rcvColl.setAdapter(collectionRcvAdapter);
        rcvColl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        ImageView backBtn = findViewById(R.id.collBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private ArrayList<CollectionRcvModel> getListCollection(){
        ArrayList<CollectionRcvModel> list = new ArrayList<>();
        //TODO 鍾 星星~~~~~~~~~~~~~~





        return list;

    }

}
//嗨