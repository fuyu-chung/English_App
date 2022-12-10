package com.example.english_app.colleges.vocabulary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;

import java.util.ArrayList;

public class VocabularyRcvAdapter extends RecyclerView.Adapter<VocabularyRcvAdapter.VocabularyRcvViewHolder> {

    //list of vocabulary items
    public ArrayList<VocabularyRcvModel> vocabularyList;

    public VocabularyRcvAdapter(ArrayList<VocabularyRcvModel> vocabularyList) {
        this.vocabularyList = vocabularyList;
    }

    public class VocabularyRcvViewHolder extends RecyclerView.ViewHolder {
        public TextView vocText,chText;
        LinearLayout linearLayout;
        //之後要補btn


        public VocabularyRcvViewHolder(@NonNull View itemView) {
            super(itemView);
            vocText = itemView.findViewById(R.id.vocabulary);
            chText = itemView.findViewById(R.id.chinese);
            linearLayout = itemView.findViewById(R.id.vocabulary_rcv_linearlayout);

        }
    }

    @NonNull
    @Override
    public VocabularyRcvAdapter.VocabularyRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vocabulary_rcv_item, parent, false);
        return new VocabularyRcvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyRcvAdapter.VocabularyRcvViewHolder holder, int position) {
        VocabularyRcvModel currentItem=vocabularyList.get(position);
        holder.vocText.setText(currentItem.getVocText());
        holder.chText.setText(currentItem.getChText());
    }

    @Override
    public int getItemCount() {
        return vocabularyList.size();
    }


}