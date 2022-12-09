package com.example.english_app.colleges.vocab.DRcvInterface;

import com.example.english_app.colleges.vocab.DynamicRcvModel;

import java.util.ArrayList;

public interface UpdateRecyclerView {
    public void callback(int position, ArrayList<DynamicRcvModel> items);
}
