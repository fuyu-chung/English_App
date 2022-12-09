package com.example.english_app.colleges.vocabulary.DRcvInterface;

import com.example.english_app.colleges.vocabulary.DynamicRcvModel;

import java.util.ArrayList;

public interface UpdateRecyclerView {
    public void callback(int position, ArrayList<DynamicRcvModel> items);
}
