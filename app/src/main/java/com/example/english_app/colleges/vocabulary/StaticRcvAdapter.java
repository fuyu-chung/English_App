package com.example.english_app.colleges.vocabulary;

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
import com.example.english_app.colleges.vocabulary.DRcvInterface.UpdateRecyclerView;
import com.example.english_app.colleges.vocabulary.VRcvInterface.CheckWhatTitleInterface;

import java.util.ArrayList;

public class StaticRcvAdapter extends RecyclerView.Adapter<StaticRcvAdapter.StaticRcvViewHolder> {

    private final ArrayList<StaticRcvModel> catItems;
    int check_position = 0; //-1為no default，0為第一選項clicked
    UpdateRecyclerView updateRecyclerView;
    Activity activity;
    boolean check = true;

    CheckWhatTitleInterface checkWhatTitleInterface;

    public StaticRcvAdapter(ArrayList<StaticRcvModel> catItems, Activity activity, UpdateRecyclerView updateRecyclerView, CheckWhatTitleInterface checkWhatTitleInterface) {
        this.catItems = catItems;
        this.activity = activity;
        this.updateRecyclerView = updateRecyclerView;
        this.checkWhatTitleInterface = checkWhatTitleInterface;
    }

    @NonNull
    @Override
    public StaticRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rcv_item, parent, false);
        return new StaticRcvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRcvViewHolder holder, final int position) {
        StaticRcvModel currentItem = catItems.get(position);
        holder.vocImage.setImageResource(currentItem.getImage());
        holder.vocText.setText(currentItem.getText());


        //確定是哪個catItem 被點擊，以轉換下方的unitItem
        if (check) {
            ArrayList<DynamicRcvModel> unitItem = new ArrayList<>();
            unitItem.add(new DynamicRcvModel("Unit 01", "#CEB443"));
            unitItem.add(new DynamicRcvModel("Unit 02", "#CEB443"));
            unitItem.add(new DynamicRcvModel("Unit 03", "#CEB443"));
            unitItem.add(new DynamicRcvModel("Unit 04", "#CEB443"));
            unitItem.add(new DynamicRcvModel("Unit 05", "#CEB443"));
            unitItem.add(new DynamicRcvModel("Unit 06", "#CEB443"));
            unitItem.add(new DynamicRcvModel("Unit 07", "#CEB443"));
            unitItem.add(new DynamicRcvModel("Unit 08", "#CEB443"));
            unitItem.add(new DynamicRcvModel("Unit 09", "#CEB443"));
            unitItem.add(new DynamicRcvModel("Unit 10", "#CEB443"));
            unitItem.add(new DynamicRcvModel("Unit 11", "#CEB443"));

            updateRecyclerView.callback(check_position, unitItem);

            check = false;
        }

        if (check_position == position) {
            holder.vocSelectedLayout.setBackgroundResource(R.drawable.voc_cat_rcv_selected);
            holder.vocText.setTextColor(Color.parseColor("#A18720"));
        } else {
            holder.vocSelectedLayout.setBackgroundResource(R.drawable.voc_cat_rcv_bg);
            holder.vocText.setTextColor(Color.parseColor("#8F9193"));
        }

    }

    @Override
    public int getItemCount() {
        return catItems.size();
    }

    public class StaticRcvViewHolder extends RecyclerView.ViewHolder {

        private final TextView vocText;
        private final ImageView vocImage;
        private final LinearLayout vocSelectedLayout;


        public StaticRcvViewHolder(@NonNull View itemView) {
            super(itemView);
            vocImage = itemView.findViewById(R.id.vocCatImage);
            vocText = itemView.findViewById(R.id.vocCatText);
            vocSelectedLayout = itemView.findViewById(R.id.voc_rcv_linearlayout);

            //得到現在的item position
            vocSelectedLayout.setOnClickListener(v -> checkPosition(getAbsoluteAdapterPosition()));


        }

        private void checkPosition(int adapterPosition) {
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(check_position);
            check_position = adapterPosition;
            notifyItemChanged(check_position);

            if (checkWhatTitleInterface != null) {
                checkWhatTitleInterface.onTitleClicked(check_position);
            }


            //elem
            if (check_position == 0) {
                ArrayList<DynamicRcvModel> unitItem = new ArrayList<>();
                for (int i = 1; i <= 11; i++) {
                    String units;
                    if (i <= 9) {
                        units = "Unit 0" + i;
                        unitItem.add(new DynamicRcvModel(units, "#CEB443"));
                    } else {
                        units = "Unit " + i;
                        unitItem.add(new DynamicRcvModel(units, "#CEB443"));
                    }
                }
                updateRecyclerView.callback(check_position, unitItem);

            }
            //jhs
            else if (check_position == 1) {
                ArrayList<DynamicRcvModel> unitItem = new ArrayList<>();
                for (int i = 1; i <= 25; i++) {
                    String units;
                    if (i <= 9) {
                        units = "Unit 0" + i;
                        unitItem.add(new DynamicRcvModel(units, "#3FA0B5"));
                    } else {
                        units = "Unit " + i;
                        unitItem.add(new DynamicRcvModel(units, "#3FA0B5"));
                    }
                }
                updateRecyclerView.callback(check_position, unitItem);

            }
            //hs
            else if (check_position == 2) {
                ArrayList<DynamicRcvModel> unitItem = new ArrayList<>();
                for (int i = 1; i <= 125; i++) {
                    String units;
                    if (i <= 9) {
                        units = "Unit 0" + i;
                        unitItem.add(new DynamicRcvModel(units, "#CEB443"));
                    } else {
                        units = "Unit " + i;
                        unitItem.add(new DynamicRcvModel(units, "#CEB443"));
                    }
                }
                updateRecyclerView.callback(check_position, unitItem);

            }

            //TOEIC
            else if (check_position == 3) {
                ArrayList<DynamicRcvModel> unitItem = new ArrayList<>();
                for (int i = 1; i <= 50; i++) {
                    String units;
                    if (i <= 9) {
                        units = "Unit 0" + i;
                        unitItem.add(new DynamicRcvModel(units, "#3FA0B5"));
                    } else {
                        units = "Unit " + i;
                        unitItem.add(new DynamicRcvModel(units, "#3FA0B5"));
                    }
                }
                updateRecyclerView.callback(check_position, unitItem);

            }
            //TOEFL
            else if (check_position == 4) {
                ArrayList<DynamicRcvModel> unitItem = new ArrayList<>();
                for (int i = 1; i <= 50; i++) {
                    String units;
                    if (i <= 9) {
                        units = "Unit 0" + i;
                        unitItem.add(new DynamicRcvModel(units, "#CEB443"));
                    } else {
                        units = "Unit " + i;
                        unitItem.add(new DynamicRcvModel(units, "#CEB443"));
                    }
                }
                updateRecyclerView.callback(check_position, unitItem);

            }
        }

    }
}
