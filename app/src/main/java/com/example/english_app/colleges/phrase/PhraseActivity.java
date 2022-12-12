package com.example.english_app.colleges.phrase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.english_app.R;
import com.example.english_app.colleges.phrase.phraseInterface.CheckWhatPhraseInterface;
import com.example.english_app.colleges.phrase.phraseInterface.RecyclerPhraseViewInterface;
import com.example.english_app.colleges.phrase.phraseInterface.UpdatePhraseRecyclerView;
import com.example.english_app.user_dorm.collections.CollectionDynamicRcvModel;
import com.example.english_app.user_dorm.collections.CollectionInterface.CheckWhatCollectInterface;
import com.example.english_app.user_dorm.collections.CollectionInterface.RecyclerColViewInterface;
import com.example.english_app.user_dorm.collections.CollectionInterface.UpdateCollectionRecyclerView;

import java.util.ArrayList;

public class PhraseActivity extends AppCompatActivity implements UpdatePhraseRecyclerView, RecyclerPhraseViewInterface, CheckWhatPhraseInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase);
    }

    @Override
    public void onClicked(int position) {

    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void callback(int position, ArrayList<CollectionDynamicRcvModel> items) {

    }
}