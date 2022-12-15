package com.example.english_app.colleges.phrase;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.colleges.phrase.phraseInterface.CheckWhatPhraseInterface;
import com.example.english_app.colleges.phrase.phraseInterface.UpdatePhraseRecyclerView;

import java.util.ArrayList;

public class PhraseStaticRcvAdapter extends RecyclerView.Adapter<PhraseStaticRcvAdapter.PhraseStaticRcvViewHolder> {

    private final ArrayList<PhraseStaticRcvModel> catItems;
    int check_position = 0; //-1為no default，0為第一選項clicked
    UpdatePhraseRecyclerView updatePhraseRecyclerView;
    Activity activity;
    boolean check = true;

    CheckWhatPhraseInterface checkWhatPhraseInterface;

    public PhraseStaticRcvAdapter(ArrayList<PhraseStaticRcvModel> catItems, Activity activity, UpdatePhraseRecyclerView updatePhraseRecyclerView, CheckWhatPhraseInterface checkWhatPhraseInterface) {
        this.catItems = catItems;
        this.activity = activity;
        this.updatePhraseRecyclerView = updatePhraseRecyclerView;
        this.checkWhatPhraseInterface = checkWhatPhraseInterface;
    }

    @NonNull
    @Override
    public PhraseStaticRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stativ_phrase_rcv_item, parent, false);
        return new PhraseStaticRcvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhraseStaticRcvViewHolder holder, final int position) {
        PhraseStaticRcvModel currentItem = catItems.get(position);
        holder.phraseImage.setImageResource(currentItem.getImage());
        holder.phraseText.setText(currentItem.getText());
        holder.phraseText2.setText(currentItem.getText2());


        //確定是哪個catItem 被點擊，以轉換下方的phraseItem
        if (check) {
            ArrayList<PhraseDynamicRcvModel> phraseItem = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                String units;
                if (i <= 9) {
                    units = "Unit 0" + i;
                    phraseItem.add(new PhraseDynamicRcvModel(units, "#CEB443"));
                } else {
                    units = "Unit " + i;
                    phraseItem.add(new PhraseDynamicRcvModel(units, "#CEB443"));
                }
            }

            updatePhraseRecyclerView.callback(check_position, phraseItem);
            check = false;
        }

        if (check_position == position) {
            holder.phraseSelectedLayout.setBackgroundResource(R.drawable.phrase_cat_rcv_select);
            holder.phraseText.setTextColor(Color.parseColor("#A18720"));
        } else {
            holder.phraseSelectedLayout.setBackgroundResource(R.drawable.voc_unit_rcv_bg);
            holder.phraseText.setTextColor(Color.parseColor("#8F9193"));
        }

    }

    @Override
    public int getItemCount() {
        return catItems.size();
    }

    public class PhraseStaticRcvViewHolder extends RecyclerView.ViewHolder {

        private final TextView phraseText;
        private final TextView phraseText2;
        private final ImageView phraseImage;
        private final LinearLayout phraseSelectedLayout;


        public PhraseStaticRcvViewHolder(@NonNull View itemView) {
            super(itemView);
            phraseImage = itemView.findViewById(R.id.phraseCatImage);
            phraseText = itemView.findViewById(R.id.phraseCatText);
            phraseText2 = itemView.findViewById(R.id.phraseHowManyUnitText);
            phraseSelectedLayout = itemView.findViewById(R.id.phrase_rcv_linearlayout);

            //得到現在的item position
            phraseSelectedLayout.setOnClickListener(v -> checkPosition(getAbsoluteAdapterPosition()));
        }

        private void checkPosition(int adapterPosition) {
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(check_position);
            check_position = adapterPosition;
            notifyItemChanged(check_position);


            //ALL
            if (check_position == 0) {
                ArrayList<PhraseDynamicRcvModel> phraseItem = new ArrayList<>();
                for (int i = 1; i <= 12; i++) {
                    String units;
                    if (i <= 9) {
                        units = "Unit 0" + i;
                        phraseItem.add(new PhraseDynamicRcvModel(units, "#CEB443"));
                    } else {
                        units = "Unit " + i;
                        phraseItem.add(new PhraseDynamicRcvModel(units, "#CEB443"));
                    }
                }
                updatePhraseRecyclerView.callback(check_position, phraseItem);
                checkWhatPhraseInterface.onClicked(check_position);
            }
            //學測
            else if (check_position == 1) {
                ArrayList<PhraseDynamicRcvModel> phraseItem = new ArrayList<>();
                for (int i = 1; i <= 4; i++) {
                    String units;
                    if (i <= 9) {
                        units = "Unit 0" + i;
                        phraseItem.add(new PhraseDynamicRcvModel(units, "#3FA0B5"));
                    } else {
                        units = "Unit " + i;
                        phraseItem.add(new PhraseDynamicRcvModel(units, "#3FA0B5"));
                    }
                }
                updatePhraseRecyclerView.callback(check_position, phraseItem);
                checkWhatPhraseInterface.onClicked(check_position);
            }
            //指考
            else if (check_position == 2) {
                ArrayList<PhraseDynamicRcvModel> phraseItem = new ArrayList<>();
                for (int i = 1; i <= 3; i++) {
                    String units;
                    if (i <= 9) {
                        units = "Unit 0" + i;
                        phraseItem.add(new PhraseDynamicRcvModel(units, "#CEB443"));
                    } else {
                        units = "Unit " + i;
                        phraseItem.add(new PhraseDynamicRcvModel(units, "#CEB443"));
                    }
                }
                updatePhraseRecyclerView.callback(check_position, phraseItem);
                checkWhatPhraseInterface.onClicked(check_position);
            }

        }

    }
}
