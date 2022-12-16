package com.example.english_app.user_dorm.wrong;

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
import com.example.english_app.user_dorm.wrong.WrongInterface.CheckWhatWrongInterface;
import com.example.english_app.user_dorm.wrong.WrongInterface.UpdateWrongRecyclerView;

import java.util.ArrayList;

public class WrongStaticRcvAdapter extends RecyclerView.Adapter<WrongStaticRcvAdapter.WrongStaticRcvViewHolder> {

    private final ArrayList<WrongStaticRcvModel> catItems;
    int check_position = 0; //-1為no default，0為第一選項clicked
    UpdateWrongRecyclerView updateWrongRecyclerView;
    Activity activity;
    boolean check = true;

    CheckWhatWrongInterface checkWhatWrongInterface;

    public WrongStaticRcvAdapter(ArrayList<WrongStaticRcvModel> catItems, Activity activity, UpdateWrongRecyclerView updateWrongRecyclerView, CheckWhatWrongInterface checkWhatWrongInterface) {
        this.catItems = catItems;
        this.activity = activity;
        this.updateWrongRecyclerView = updateWrongRecyclerView;
        this.checkWhatWrongInterface = checkWhatWrongInterface;
    }

    @NonNull
    @Override
    public WrongStaticRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //跟Competoition用一樣的
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_com_rcv_item, parent, false);
        return new WrongStaticRcvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WrongStaticRcvViewHolder holder, final int position) {
        WrongStaticRcvModel currentItem = catItems.get(position);
        holder.wrongImage.setImageResource(currentItem.getImage());
        holder.wrongText.setText(currentItem.getText());
        holder.wrongText2.setText(currentItem.getText2());


        //確定是哪個catItem 被點擊，以轉換下方的wrongItem
        if (check) {
            ArrayList<WrongDynamicRcvModel> wrongItem = new ArrayList<>();
            wrongItem.add(new WrongDynamicRcvModel("國小單字 - 錯誤題", "#CEB443"));
            wrongItem.add(new WrongDynamicRcvModel("國中單字 - 錯誤題", "#CEB443"));
            wrongItem.add(new WrongDynamicRcvModel("高中單字 - 錯誤題", "#CEB443"));
            wrongItem.add(new WrongDynamicRcvModel("多益單字 - 錯誤題", "#CEB443"));
            wrongItem.add(new WrongDynamicRcvModel("托福單字 - 錯誤題", "#CEB443"));
            wrongItem.add(new WrongDynamicRcvModel("學測單字 - 錯誤題", "#CEB443"));
            wrongItem.add(new WrongDynamicRcvModel("指考單字 - 錯誤題", "#CEB443"));

            updateWrongRecyclerView.callback(check_position, wrongItem);
            check = false;
        }

        if (check_position == position) {
            //跟Collection 設一樣的背景
            holder.wrongSelectedLayout.setBackgroundResource(R.drawable.col_rcv_selected);
            holder.wrongText.setTextColor(Color.parseColor("#A18720"));
        } else {
            holder.wrongSelectedLayout.setBackgroundResource(R.drawable.col_rcv_bg);
            holder.wrongText.setTextColor(Color.parseColor("#8F9193"));
        }

    }

    @Override
    public int getItemCount() {
        return catItems.size();
    }

    public class WrongStaticRcvViewHolder extends RecyclerView.ViewHolder {

        private final TextView wrongText;
        private final TextView wrongText2;
        private final ImageView wrongImage;
        private final LinearLayout wrongSelectedLayout;


        public WrongStaticRcvViewHolder(@NonNull View itemView) {
            super(itemView);
            //跟Competition用一樣的
            wrongImage = itemView.findViewById(R.id.comCatImage);
            wrongText = itemView.findViewById(R.id.comCatText);
            wrongText2 = itemView.findViewById(R.id.comHowManyUnitText);
            wrongSelectedLayout = itemView.findViewById(R.id.com_rcv_linearlayout);

            //得到現在的item position
            wrongSelectedLayout.setOnClickListener(v -> checkPosition(getAbsoluteAdapterPosition()));
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
                ArrayList<WrongDynamicRcvModel> wrongItem = new ArrayList<>();

                wrongItem.add(new WrongDynamicRcvModel("國小單字 - 錯誤題", "#CEB443"));
                wrongItem.add(new WrongDynamicRcvModel("國中單字 - 錯誤題", "#CEB443"));
                wrongItem.add(new WrongDynamicRcvModel("高中單字 - 錯誤題", "#CEB443"));
                wrongItem.add(new WrongDynamicRcvModel("多益單字 - 錯誤題", "#CEB443"));
                wrongItem.add(new WrongDynamicRcvModel("托福單字 - 錯誤題", "#CEB443"));
                wrongItem.add(new WrongDynamicRcvModel("學測單字 - 錯誤題", "#CEB443"));
                wrongItem.add(new WrongDynamicRcvModel("指考單字 - 錯誤題", "#CEB443"));

                updateWrongRecyclerView.callback(check_position, wrongItem);
                checkWhatWrongInterface.onClicked(check_position);
            }
            //片語
            else if (check_position == 1) {
                ArrayList<WrongDynamicRcvModel> wrongItem = new ArrayList<>();
                wrongItem.add(new WrongDynamicRcvModel("ALL片語 - 錯誤題", "#3FA0B5"));
                wrongItem.add(new WrongDynamicRcvModel("學測片語 - 錯誤題", "#3FA0B5"));
                wrongItem.add(new WrongDynamicRcvModel("指考片語 - 錯誤題", "#3FA0B5"));
                updateWrongRecyclerView.callback(check_position, wrongItem);
                checkWhatWrongInterface.onClicked(check_position);
            }

        }

    }
}
