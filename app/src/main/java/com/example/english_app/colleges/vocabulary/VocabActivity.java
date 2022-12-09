package com.example.english_app.colleges.vocabulary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.english_app.R;
import com.example.english_app.colleges.vocab.DRcvInterface.LoadMore;
import com.example.english_app.colleges.vocab.DRcvInterface.UpdateRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VocabActivity extends AppCompatActivity implements UpdateRecyclerView {

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
        dynamicRcvAdapter = new DynamicRcvAdapter(unitItem);
        rcvVocUnit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvVocUnit.setAdapter(dynamicRcvAdapter);



    }

    @Override
    public void callback(int position, ArrayList<DynamicRcvModel> items) {
        dynamicRcvAdapter = new DynamicRcvAdapter(items);
        dynamicRcvAdapter.notifyDataSetChanged();
        rcvVocUnit.setAdapter((dynamicRcvAdapter));

    }
}

//unitItem.add(new DynamicRcvModel("Unit 01"));
//        unitItem.add(new DynamicRcvModel("Unit 02"));
//        unitItem.add(new DynamicRcvModel("Unit 03"));
//        unitItem.add(new DynamicRcvModel("Unit 04"));
//        unitItem.add(new DynamicRcvModel("Unit 05"));
//        unitItem.add(new DynamicRcvModel("Unit 06"));
//        unitItem.add(new DynamicRcvModel("Unit 07"));
//        unitItem.add(new DynamicRcvModel("Unit 08"));
//        unitItem.add(new DynamicRcvModel("Unit 09"));
//        unitItem.add(new DynamicRcvModel("Unit 10"));
//        unitItem.add(new DynamicRcvModel("Unit 11"));
//
//        RecyclerView drv = findViewById(R.id.voc_rcv_unit);
//        drv.setLayoutManager(new LinearLayoutManager(this));
//        dynamicRcvAdapter = new DynamicRcvAdapter(drv,this,unitItem);
//        drv.setAdapter(dynamicRcvAdapter);
//
//        dynamicRcvAdapter.setLoadMore(new LoadMore() {
//@Override
//public void onLoadMore() {
//        if (unitItem.size()<=10){
//        catItem.add(null);
//        dynamicRcvAdapter.notifyItemInserted(unitItem.size()-1);
//        new Handler().postDelayed(new Runnable() {
//@Override
//public void run() {
//        unitItem.remove(unitItem.size()-1);
//        dynamicRcvAdapter.notifyItemRemoved(unitItem.size());
//
//        int index = unitItem.size();
//        int end = index+10;
//        for(int i = index; i<end; i++){
//        String name = UUID.randomUUID().toString();
//        DynamicRcvModel item = new DynamicRcvModel(name);
//        unitItem.add(item);
//        }
//        dynamicRcvAdapter.notifyDataSetChanged();
//        dynamicRcvAdapter.setLoaded();
//        }
//        }, 2000);//delay 的loading 時間
//        }
//        else
//        Toast.makeText(VocabActivity.this, "已顯示全部單元", Toast.LENGTH_SHORT).show();
//        }
//        });