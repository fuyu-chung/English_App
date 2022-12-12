package com.example.english_app.user_dorm.collections;

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
import com.example.english_app.user_dorm.collections.CollectionInterface.CheckWhatCollectInterface;
import com.example.english_app.user_dorm.collections.CollectionInterface.UpdateCollectionRecyclerView;

import java.util.ArrayList;

public class CollectionStaticRcvAdapter extends RecyclerView.Adapter<CollectionStaticRcvAdapter.CollectionStaticRcvViewHolder> {

    private final ArrayList<CollectionStaticRcvModel> catItems;
    int check_position = 0; //-1為no default，0為第一選項clicked
    UpdateCollectionRecyclerView updateCollectionRecyclerView;
    Activity activity;
    boolean check = true;

    CheckWhatCollectInterface checkWhatCollectInterface;

    public CollectionStaticRcvAdapter(ArrayList<CollectionStaticRcvModel> catItems, Activity activity, UpdateCollectionRecyclerView updateCollectionRecyclerView, CheckWhatCollectInterface checkWhatCollectInterface) {
        this.catItems = catItems;
        this.activity = activity;
        this.updateCollectionRecyclerView = updateCollectionRecyclerView;
        this.checkWhatCollectInterface = checkWhatCollectInterface;
    }

    @NonNull
    @Override
    public CollectionStaticRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_vocabulary_item, parent, false);
        return new CollectionStaticRcvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionStaticRcvViewHolder holder, final int position) {
        CollectionStaticRcvModel currentItem = catItems.get(position);
        holder.colImage.setImageResource(currentItem.getImage());
        holder.colText.setText(currentItem.getText());


        //確定是哪個catItem 被點擊，以轉換下方的collectItem
        if (check) {
            ArrayList<CollectionDynamicRcvModel> collectItem = new ArrayList<>();
            collectItem.add(new CollectionDynamicRcvModel("國小單字 Collections", "#CEB443"));
            collectItem.add(new CollectionDynamicRcvModel("國中單字 Collections", "#CEB443"));
            collectItem.add(new CollectionDynamicRcvModel("高中單字 Collections", "#CEB443"));
            collectItem.add(new CollectionDynamicRcvModel("多益單字 Collections", "#CEB443"));
            collectItem.add(new CollectionDynamicRcvModel("托福單字 Collections", "#CEB443"));

            updateCollectionRecyclerView.collcallback(check_position, collectItem);

            check = false;
        }

        if (check_position == position) {
            holder.colSelectedLayout.setBackgroundResource(R.drawable.col_rcv_selected);
            holder.colText.setTextColor(Color.parseColor("#A18720"));
        } else {
            holder.colSelectedLayout.setBackgroundResource(R.drawable.col_rcv_bg);
            holder.colText.setTextColor(Color.parseColor("#8F9193"));
        }

    }

    @Override
    public int getItemCount() {
        return catItems.size();
    }

    public class CollectionStaticRcvViewHolder extends RecyclerView.ViewHolder {

        private final TextView colText;
        private final ImageView colImage;
        private final LinearLayout colSelectedLayout;


        public CollectionStaticRcvViewHolder(@NonNull View itemView) {
            super(itemView);
            colImage = itemView.findViewById(R.id.collCollegeImg);
            colText = itemView.findViewById(R.id.collCollegeText);
            colSelectedLayout = itemView.findViewById(R.id.col_rcv_linearlayout);

            //得到現在的item position
            colSelectedLayout.setOnClickListener(v -> checkPosition(getAbsoluteAdapterPosition()));


        }

        private void checkPosition(int adapterPosition) {
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(check_position);
            check_position = adapterPosition;
            notifyItemChanged(check_position);


            //單字
            if (check_position == 0) {
                ArrayList<CollectionDynamicRcvModel> collectItem = new ArrayList<>();
                collectItem.add(new CollectionDynamicRcvModel("國小單字 Collections", "#CEB443"));
                collectItem.add(new CollectionDynamicRcvModel("國中單字 Collections", "#CEB443"));
                collectItem.add(new CollectionDynamicRcvModel("高中單字 Collections", "#CEB443"));
                collectItem.add(new CollectionDynamicRcvModel("多益單字 Collections", "#CEB443"));
                collectItem.add(new CollectionDynamicRcvModel("托福單字 Collections", "#CEB443"));
                updateCollectionRecyclerView.collcallback(check_position, collectItem);
                checkWhatCollectInterface.onCollectClicked(check_position);
            }
            //片語
            else if (check_position == 1) {
                ArrayList<CollectionDynamicRcvModel> collectItem = new ArrayList<>();
                collectItem.add(new CollectionDynamicRcvModel("全片語 Collections", "#3FA0B5"));
                collectItem.add(new CollectionDynamicRcvModel("學測片語 Collections", "#3FA0B5"));
                collectItem.add(new CollectionDynamicRcvModel("指考片語 Collections", "#3FA0B5"));

                updateCollectionRecyclerView.collcallback(check_position, collectItem);
                checkWhatCollectInterface.onCollectClicked(check_position);
            }
            //題目
            else if (check_position == 2) {
                ArrayList<CollectionDynamicRcvModel> collectItem = new ArrayList<>();
                collectItem.add(new CollectionDynamicRcvModel("國小單字 Collections", "#CEB443"));
                collectItem.add(new CollectionDynamicRcvModel("國中單字 Collections", "#CEB443"));
                collectItem.add(new CollectionDynamicRcvModel("高中單字 Collections", "#CEB443"));
                collectItem.add(new CollectionDynamicRcvModel("多益單字 Collections", "#CEB443"));
                collectItem.add(new CollectionDynamicRcvModel("托福單字 Collections", "#CEB443"));
                updateCollectionRecyclerView.collcallback(check_position, collectItem);
                checkWhatCollectInterface.onCollectClicked(check_position);
            }

        }

    }
}
