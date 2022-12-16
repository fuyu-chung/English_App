package com.example.english_app.colleges.competition;

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
import com.example.english_app.colleges.competition.competitionInterface.CheckWhatComInterface;
import com.example.english_app.colleges.competition.competitionInterface.UpdateComRecyclerView;

import java.util.ArrayList;

public class ComStaticRcvAdapter extends RecyclerView.Adapter<ComStaticRcvAdapter.ComStaticRcvViewHolder> {

    private final ArrayList<ComStaticRcvModel> catItems;
    int check_position = 0; //-1為no default，0為第一選項clicked
    UpdateComRecyclerView updateComRecyclerView;
    Activity activity;
    boolean check = true;

    CheckWhatComInterface checkWhatComInterface;

    public ComStaticRcvAdapter(ArrayList<ComStaticRcvModel> catItems, Activity activity, UpdateComRecyclerView updateComRecyclerView, CheckWhatComInterface checkWhatComInterface) {
        this.catItems = catItems;
        this.activity = activity;
        this.updateComRecyclerView = updateComRecyclerView;
        this.checkWhatComInterface = checkWhatComInterface;
    }

    @NonNull
    @Override
    public ComStaticRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_com_rcv_item, parent, false);
        return new ComStaticRcvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComStaticRcvViewHolder holder, final int position) {
        ComStaticRcvModel currentItem = catItems.get(position);
        holder.comImage.setImageResource(currentItem.getImage());
        holder.comText.setText(currentItem.getText());
        holder.comText2.setText(currentItem.getText2());


        //確定是哪個catItem 被點擊，以轉換下方的gameItem
        if (check) {
            ArrayList<ComDynamicRcvModel> gameItem = new ArrayList<>();
            gameItem.add(new ComDynamicRcvModel("國小單字 - 中翻英", "#CE8443"));
            gameItem.add(new ComDynamicRcvModel("國小單字 - 英翻中", "#CE8443"));
            gameItem.add(new ComDynamicRcvModel("國中單字 - 中翻英", "#CE8443"));
            gameItem.add(new ComDynamicRcvModel("國中單字 - 英翻中", "#CE8443"));
            gameItem.add(new ComDynamicRcvModel("高中單字 - 中翻英", "#CE8443"));
            gameItem.add(new ComDynamicRcvModel("高中單字 - 英翻中", "#CE8443"));
            gameItem.add(new ComDynamicRcvModel("多益單字 - 中翻英", "#CE8443"));
            gameItem.add(new ComDynamicRcvModel("多益單字 - 英翻中", "#CE8443"));
            gameItem.add(new ComDynamicRcvModel("托福單字 - 中翻英", "#CE8443"));
            gameItem.add(new ComDynamicRcvModel("托福單字 - 英翻中", "#CE8443"));

            updateComRecyclerView.callback(check_position, gameItem);
            check = false;
        }

        if (check_position == position) {
            holder.comSelectedLayout.setBackgroundResource(R.drawable.com_rcv_cat_selected);
            holder.comText.setTextColor(Color.parseColor("#A18720"));
        } else {
            holder.comSelectedLayout.setBackgroundResource(R.drawable.com_rcv_cat_bg);
            holder.comText.setTextColor(Color.parseColor("#8F9193"));
        }

    }

    @Override
    public int getItemCount() {
        return catItems.size();
    }

    public class ComStaticRcvViewHolder extends RecyclerView.ViewHolder {

        private final TextView comText;
        private final TextView comText2;
        private final ImageView comImage;
        private final LinearLayout comSelectedLayout;


        public ComStaticRcvViewHolder(@NonNull View itemView) {
            super(itemView);
            comImage = itemView.findViewById(R.id.comCatImage);
            comText = itemView.findViewById(R.id.comCatText);
            comText2 = itemView.findViewById(R.id.comHowManyUnitText);
            comSelectedLayout = itemView.findViewById(R.id.com_rcv_linearlayout);

            //得到現在的item position
            comSelectedLayout.setOnClickListener(v -> checkPosition(getAbsoluteAdapterPosition()));
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
                ArrayList<ComDynamicRcvModel> gameItem = new ArrayList<>();

                gameItem.add(new ComDynamicRcvModel("國小單字 - 中翻英", "#CE8443"));
                gameItem.add(new ComDynamicRcvModel("國小單字 - 英翻中", "#CE8443"));
                gameItem.add(new ComDynamicRcvModel("國中單字 - 中翻英", "#CE8443"));
                gameItem.add(new ComDynamicRcvModel("國中單字 - 英翻中", "#CE8443"));
                gameItem.add(new ComDynamicRcvModel("高中單字 - 中翻英", "#CE8443"));
                gameItem.add(new ComDynamicRcvModel("高中單字 - 英翻中", "#CE8443"));
                gameItem.add(new ComDynamicRcvModel("多益單字 - 中翻英", "#CE8443"));
                gameItem.add(new ComDynamicRcvModel("多益單字 - 英翻中", "#CE8443"));
                gameItem.add(new ComDynamicRcvModel("托福單字 - 中翻英", "#CE8443"));
                gameItem.add(new ComDynamicRcvModel("托福單字 - 英翻中", "#CE8443"));

                updateComRecyclerView.callback(check_position, gameItem);
                checkWhatComInterface.onClicked(check_position);
            }
            //學測
            else if (check_position == 1) {
                ArrayList<ComDynamicRcvModel> gameItem = new ArrayList<>();
                gameItem.add(new ComDynamicRcvModel("ALL片語 - 中翻英", "#EFB154"));
                gameItem.add(new ComDynamicRcvModel("ALL片語 - 英翻中", "#EFB154"));
                gameItem.add(new ComDynamicRcvModel("學測片語 - 中翻英", "#EFB154"));
                gameItem.add(new ComDynamicRcvModel("學測片語 - 英翻中", "#EFB154"));
                gameItem.add(new ComDynamicRcvModel("指考片語 - 中翻英", "#EFB154"));
                gameItem.add(new ComDynamicRcvModel("指考片語 - 英翻中", "#EFB154"));
                updateComRecyclerView.callback(check_position, gameItem);
                checkWhatComInterface.onClicked(check_position);
            }
            else if (check_position == 2) {
                ArrayList<ComDynamicRcvModel> gameItem = new ArrayList<>();
                gameItem.add(new ComDynamicRcvModel("學測單字題","#EFB154"));
                gameItem.add(new ComDynamicRcvModel("指考單字題","#EFB154"));
                gameItem.add(new ComDynamicRcvModel("學測/指考克漏字","#EFB154"));
                gameItem.add(new ComDynamicRcvModel("學測/指考文意選填","#EFB154"));
                updateComRecyclerView.callback(check_position, gameItem);
                checkWhatComInterface.onClicked(check_position);

            }
        }

    }
}
