package com.example.english_app.user_dorm.wrong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Layout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.english_app.R;

import java.util.ArrayList;

public class WrongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);

        RecyclerView rcvWrong = findViewById(R.id.wrong_rcv);
        WrongRcvAdapter wrongRcvAdapter = new WrongRcvAdapter(getListWrong());
        rcvWrong.setAdapter(wrongRcvAdapter);
        rcvWrong.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



        ImageView backBtn = findViewById(R.id.wrongBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private ArrayList<WrongRcvModel> getListWrong() {
        ArrayList<WrongRcvModel> list = new ArrayList<>();


        return list;
    }
}