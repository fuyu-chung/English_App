package com.example.english_app.colleges.vocabulary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.english_app.R;
import com.example.english_app.colleges.vocabulary.DRcvInterface.UpdateRecyclerView;
import com.example.english_app.colleges.vocabulary.VRcvInterface.RecyclerViewInterface;

import java.util.ArrayList;

public class VocabActivity extends AppCompatActivity implements UpdateRecyclerView, RecyclerViewInterface {

    private RecyclerView rcvVocUnit;
    private DynamicRcvAdapter dynamicRcvAdapter;
    Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);

        ArrayList<StaticRcvModel> catItem = new ArrayList<>();
        catItem.add(new StaticRcvModel(R.drawable.ic_elem, "國小"));
        catItem.add(new StaticRcvModel(R.drawable.ic_jhs, "國中"));
        catItem.add(new StaticRcvModel(R.drawable.ic_hs, "高中"));
        catItem.add(new StaticRcvModel(R.drawable.ic_toeic, "多益"));
        catItem.add(new StaticRcvModel(R.drawable.ic_toefl, "托福"));

        RecyclerView rcvVocTitle = findViewById(R.id.voc_rcv_cat);
        StaticRcvAdapter staticRcvAdapter = new StaticRcvAdapter(catItem, this, this);
        rcvVocTitle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rcvVocTitle.setAdapter(staticRcvAdapter);

        ArrayList<DynamicRcvModel> unitItem = new ArrayList<>();
        unitItem.add(new DynamicRcvModel("Unit 01","#CEB443"));
        unitItem.add(new DynamicRcvModel("Unit 02","#CEB443"));
        unitItem.add(new DynamicRcvModel("Unit 03","#CEB443"));
        unitItem.add(new DynamicRcvModel("Unit 04","#CEB443"));
        unitItem.add(new DynamicRcvModel("Unit 05","#CEB443"));
        unitItem.add(new DynamicRcvModel("Unit 06","#CEB443"));
        unitItem.add(new DynamicRcvModel("Unit 07","#CEB443"));
        unitItem.add(new DynamicRcvModel("Unit 08","#CEB443"));
        unitItem.add(new DynamicRcvModel("Unit 09","#CEB443"));
        unitItem.add(new DynamicRcvModel("Unit 10","#CEB443"));
        unitItem.add(new DynamicRcvModel("Unit 11","#CEB443"));

        rcvVocUnit = findViewById(R.id.voc_rcv_unit);
        dynamicRcvAdapter = new DynamicRcvAdapter(unitItem,this);
        rcvVocUnit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvVocUnit.setAdapter(dynamicRcvAdapter);



    }

    @Override
    public void callback(int position, ArrayList<DynamicRcvModel> items) {
        dynamicRcvAdapter = new DynamicRcvAdapter(items,this);
        dynamicRcvAdapter.notifyDataSetChanged();
        rcvVocUnit.setAdapter((dynamicRcvAdapter));

    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(VocabActivity.this,VocabElementaryActivity.class);


        startActivity(intent);
    }
}
