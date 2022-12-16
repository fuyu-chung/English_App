package com.example.english_app.user_dorm.wrong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.english_app.R;
import com.example.english_app.user_dorm.wrong.WrongInterface.CheckWhatWrongInterface;
import com.example.english_app.user_dorm.wrong.WrongInterface.RecyclerWrongViewInterface;
import com.example.english_app.user_dorm.wrong.WrongInterface.UpdateWrongRecyclerView;

import java.util.ArrayList;

public class MyWrongMainActivity extends AppCompatActivity implements UpdateWrongRecyclerView, RecyclerWrongViewInterface, CheckWhatWrongInterface {

    private RecyclerView rcvWrongGame;
    private WrongDynamicRcvAdapter wrongDynamicRcvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wrong_main);

        ArrayList<WrongStaticRcvModel> catItem = new ArrayList<>();
        catItem.add(new WrongStaticRcvModel(R.drawable.house_vocabulary, "單字錯誤", "7類"));
        catItem.add(new WrongStaticRcvModel(R.drawable.house_phrase, "片語錯誤", "3類"));
        RecyclerView rcvWrongTitle = findViewById(R.id.wrong_rcv_cat);
        WrongStaticRcvAdapter wrongStaticRcvAdapter = new WrongStaticRcvAdapter(catItem, this, this, this);
        rcvWrongTitle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcvWrongTitle.setAdapter(wrongStaticRcvAdapter);

        ArrayList<WrongDynamicRcvModel> wrongItem = new ArrayList<>();

        wrongItem.add(new WrongDynamicRcvModel("國小單字 - 錯誤題", "#CEB443"));
        wrongItem.add(new WrongDynamicRcvModel("國中單字 - 錯誤題", "#CEB443"));
        wrongItem.add(new WrongDynamicRcvModel("高中單字 - 錯誤題", "#CEB443"));
        wrongItem.add(new WrongDynamicRcvModel("多益單字 - 錯誤題", "#CEB443"));
        wrongItem.add(new WrongDynamicRcvModel("托福單字 - 錯誤題", "#CEB443"));

        rcvWrongGame = findViewById(R.id.wrong_rcv_unit);
        wrongDynamicRcvAdapter = new WrongDynamicRcvAdapter(wrongItem, this);
        rcvWrongGame.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvWrongGame.setAdapter(wrongDynamicRcvAdapter);


    }

    @Override
    public void callback(int position, ArrayList<WrongDynamicRcvModel> items) {
        wrongDynamicRcvAdapter = new WrongDynamicRcvAdapter(items, this);
        wrongDynamicRcvAdapter.notifyDataSetChanged();
        rcvWrongGame.setAdapter(wrongDynamicRcvAdapter);
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
        Intent intent = new Intent(MyWrongMainActivity.this, WrongActivity.class);
        startActivity(intent);
    }


}