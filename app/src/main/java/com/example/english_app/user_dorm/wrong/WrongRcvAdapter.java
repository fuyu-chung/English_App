package com.example.english_app.user_dorm.wrong;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.colleges.vocabulary.VocabularyRcvAdapter;
import com.example.english_app.colleges.vocabulary.VocabularyRcvModel;

import java.util.ArrayList;

public class WrongRcvAdapter extends RecyclerView.Adapter<WrongRcvAdapter.WrongRcvViewHolder> {
    public ArrayList<WrongRcvModel> wrongList;
    int check_StarPosition = -1;
    private int star_click_ct = 0;

    public WrongRcvAdapter(ArrayList<WrongRcvModel> wrongList) {
        this.wrongList = wrongList;
    }

    public class WrongRcvViewHolder extends RecyclerView.ViewHolder {
        public TextView vocText, chText;
        LinearLayout linearLayout;
        private ImageButton imgBtnStar;
        //之後要補btn

        public WrongRcvViewHolder(@NonNull View itemView) {
            super(itemView);
            //recycler
            vocText = itemView.findViewById(R.id.vocabulary);
            chText = itemView.findViewById(R.id.chinese);
            linearLayout = itemView.findViewById(R.id.vocabulary_rcv_linearlayout);
//            imgBtnStar = itemView.findViewById(R.id.vocabulary_collection_btn);
        }

        private void checkPosition(int adapterPosition) {
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(check_StarPosition);
            check_StarPosition = adapterPosition;
            notifyItemChanged(check_StarPosition);
        }
    }

    @NonNull
    @Override
    public WrongRcvAdapter.WrongRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vocabulary_rcv_item, parent, false);
        return new WrongRcvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WrongRcvAdapter.WrongRcvViewHolder holder, int position) {
        WrongRcvModel currentItem = wrongList.get(position);
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
        return wrongList.size();
    }


}
