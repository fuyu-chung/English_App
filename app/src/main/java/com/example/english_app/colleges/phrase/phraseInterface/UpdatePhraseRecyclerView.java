package com.example.english_app.colleges.phrase.phraseInterface;

import com.example.english_app.user_dorm.collections.CollectionDynamicRcvModel;

import java.util.ArrayList;

public interface UpdatePhraseRecyclerView {
    public void callback(int position, ArrayList<CollectionDynamicRcvModel> items);
}
