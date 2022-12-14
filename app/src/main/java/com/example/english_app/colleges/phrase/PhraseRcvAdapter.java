package com.example.english_app.colleges.phrase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.colleges.vocabulary.CheckStarClickInterface;

import java.util.ArrayList;

public class PhraseRcvAdapter extends RecyclerView.Adapter<PhraseRcvAdapter.VocabularyRcvViewHolder> {

    //list of vocabulary items
    public ArrayList<PhraseRcvModel> phraseList;
    int check_StarPosition = -1; //沒有任何星星被選擇
    private int star_click_ct = 0;

    CheckStarClickInterface checkStarClickInterface;

    public PhraseRcvAdapter(ArrayList<PhraseRcvModel> phraseList, CheckStarClickInterface checkStarClickInterface) {
        this.phraseList = phraseList;
        this.checkStarClickInterface = checkStarClickInterface;
    }

    public class VocabularyRcvViewHolder extends RecyclerView.ViewHolder {
        public TextView vocText, chText;
        LinearLayout linearLayout;
        private ImageButton imgBtnStar;
        //之後要補btn


        public VocabularyRcvViewHolder(@NonNull View itemView) {
            super(itemView);
            vocText = itemView.findViewById(R.id.vocabulary);
            chText = itemView.findViewById(R.id.chinese);
            linearLayout = itemView.findViewById(R.id.vocabulary_rcv_linearlayout);
            imgBtnStar = itemView.findViewById(R.id.vocabulary_collection_btn);

            //星星取得按下的position
            imgBtnStar.setOnClickListener(v -> {
                checkPosition(getAbsoluteAdapterPosition());
                System.out.println("star click!phrase " + getAbsoluteAdapterPosition());
//                System.out.println("title" + );
            });

        }

        private void checkPosition(int adapterPosition) {
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(check_StarPosition);
            check_StarPosition = adapterPosition;
            notifyItemChanged(check_StarPosition);
            checkStarClickInterface.onStarClicked(check_StarPosition);
            check_StarPosition = -1;
        }
    }

    @NonNull
    @Override
    public PhraseRcvAdapter.VocabularyRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vocabulary_rcv_item, parent, false);
        return new VocabularyRcvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhraseRcvAdapter.VocabularyRcvViewHolder holder, int position) {
        PhraseRcvModel currentItem = phraseList.get(position);
        holder.vocText.setText(currentItem.getVocText());
        holder.chText.setText(currentItem.getChText());
        //btn
        if (check_StarPosition == position) {
            star_click_ct = 0;
//            holder.imgBtnStar.setImageResource(R.drawable.star_click);


        }
    }

    @Override
    public int getItemCount() {
        return phraseList.size();
    }
}



