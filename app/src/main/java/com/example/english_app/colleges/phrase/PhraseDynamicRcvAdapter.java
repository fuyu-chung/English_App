package com.example.english_app.colleges.phrase;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.colleges.phrase.phraseInterface.RecyclerPhraseViewInterface;

import java.util.ArrayList;

public class PhraseDynamicRcvAdapter extends RecyclerView.Adapter<PhraseDynamicRcvAdapter.PhraseDynamicRcvHolder> {

    private final RecyclerPhraseViewInterface recyclerPhraseViewInterface;
    int check_position = -1;

    public ArrayList<PhraseDynamicRcvModel> cDynamicRcvModels;

    public PhraseDynamicRcvAdapter(ArrayList<PhraseDynamicRcvModel> cDynamicRcvModels, RecyclerPhraseViewInterface recyclerPhraseViewInterface) {
        this.cDynamicRcvModels = cDynamicRcvModels;
        this.recyclerPhraseViewInterface = recyclerPhraseViewInterface;
    }

    public class PhraseDynamicRcvHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        ConstraintLayout constraintLayout;


        public PhraseDynamicRcvHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.voc_rcv_unitText);
            constraintLayout = itemView.findViewById(R.id.voc_unit_layout);
            constraintLayout.setOnClickListener(v -> checkPosition(getAbsoluteAdapterPosition()));
        }

        private void checkPosition(int adapterPosition) {
            check_position = -1;
            System.out.println(check_position);
            System.out.println(adapterPosition);

            if (adapterPosition == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(check_position);
            check_position = adapterPosition;
            notifyItemChanged(check_position);

            System.out.println(check_position);
            System.out.println(adapterPosition);

            if (recyclerPhraseViewInterface != null) {
                recyclerPhraseViewInterface.onItemClicked(check_position);
            }
        }
    }

    @NonNull
    @Override
    public PhraseDynamicRcvAdapter.PhraseDynamicRcvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rcv_item, parent, false);
        return new PhraseDynamicRcvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhraseDynamicRcvAdapter.PhraseDynamicRcvHolder holder, int position) {
        PhraseDynamicRcvModel currentItem = cDynamicRcvModels.get(position);
        holder.textView.setText(currentItem.getUnitText());
        holder.textView.setTextColor(Color.parseColor(currentItem.getColor()));//改變顏色
        holder.constraintLayout.setBackgroundResource(R.drawable.voc_unit_rcv_bg);
    }

    @Override
    public int getItemCount() {
        return cDynamicRcvModels.size();
    }
}

