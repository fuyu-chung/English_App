package com.example.english_app.colleges.competition.competitionInterface;

import com.example.english_app.colleges.competition.ComDynamicRcvModel;
import com.example.english_app.colleges.phrase.PhraseDynamicRcvModel;

import java.util.ArrayList;

public interface UpdateComRecyclerView {
    public void callback(int position, ArrayList<ComDynamicRcvModel> items);
}
