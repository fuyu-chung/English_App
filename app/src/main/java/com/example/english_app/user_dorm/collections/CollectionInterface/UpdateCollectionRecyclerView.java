package com.example.english_app.user_dorm.collections.CollectionInterface;

import com.example.english_app.colleges.vocabulary.DynamicRcvModel;
import com.example.english_app.user_dorm.collections.CollectionDynamicRcvModel;

import java.util.ArrayList;

public interface UpdateCollectionRecyclerView {
    public void collcallback(int position, ArrayList<CollectionDynamicRcvModel> items);
}
