package com.example.english_app.colleges.vocabulary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.colleges.vocabulary.DRcvInterface.UpdateRecyclerView;
import com.example.english_app.colleges.vocabulary.VRcvInterface.CheckWhatTitleInterface;
import com.example.english_app.colleges.vocabulary.VRcvInterface.RecyclerViewInterface;

import java.util.ArrayList;

public class VocabActivity extends AppCompatActivity implements UpdateRecyclerView, RecyclerViewInterface, CheckWhatTitleInterface {

    private RecyclerView rcvVocUnit;
    private DynamicRcvAdapter dynamicRcvAdapter;
    private StaticRcvAdapter staticRcvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);

        ArrayList<StaticRcvModel> catItem = new ArrayList<>();
        catItem.add(new StaticRcvModel(R.drawable.ic_elem, "國小", "11單元"));
        catItem.add(new StaticRcvModel(R.drawable.ic_jhs, "國中", "25單元"));
        catItem.add(new StaticRcvModel(R.drawable.ic_hs, "高中", "125單元"));
        catItem.add(new StaticRcvModel(R.drawable.ic_toeic, "多益", "?單元"));
        catItem.add(new StaticRcvModel(R.drawable.ic_toefl, "托福", "51單元"));

        RecyclerView rcvVocTitle = findViewById(R.id.voc_rcv_cat);
        staticRcvAdapter = new StaticRcvAdapter(catItem, this, this,this);
        rcvVocTitle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rcvVocTitle.setAdapter(staticRcvAdapter);

        ArrayList<DynamicRcvModel> unitItem = new ArrayList<>();
        for (int i = 1; i <= 11; i++){
            String units;
            if (i <= 9){
                units = "Unit 0" + i;
                unitItem.add(new DynamicRcvModel(units,"#CEB443"));
            }
            else {
                units = "Unit " + i;
                unitItem.add(new DynamicRcvModel(units,"#CEB443"));
            }
        }

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
    public void onTitleClicked(int position) {
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        sharedPreferences.edit().putInt("title", position).apply();

    }

    @Override
    public void onItemClicked(int position) {
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        sharedPreferences.edit().putInt("position", position).apply();
        Intent intent = new Intent(VocabActivity.this,VocabElementaryActivity.class);
        startActivity(intent);
    }
}
