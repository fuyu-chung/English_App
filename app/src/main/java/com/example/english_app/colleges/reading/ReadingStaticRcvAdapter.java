package com.example.english_app.colleges.reading;

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

import java.util.ArrayList;

public class ReadingStaticRcvAdapter extends RecyclerView.Adapter<ReadingStaticRcvAdapter.ReadingStaticRcvViewHolder> {
    private final ArrayList<ReadingStaticRcvModel> newsItems;
    int check_position = 0; //-1為no default，0為第一選項clicked
    UpdateNewsRcv updateNewsRcv;
    Activity activity;
    boolean check = true;

    CheckWhatNewsInterface checkWhatNewsInterface;

    public ReadingStaticRcvAdapter(ArrayList<ReadingStaticRcvModel> newsItems, Activity activity, UpdateNewsRcv updateNewsRcv, CheckWhatNewsInterface checkWhatNewsInterface) {
        this.newsItems = newsItems;
        this.activity = activity;
        this.updateNewsRcv = updateNewsRcv;
        this.checkWhatNewsInterface = checkWhatNewsInterface;
    }


    @NonNull
    @Override
    public ReadingStaticRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_reading_rcv_item, parent, false);
        return new ReadingStaticRcvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadingStaticRcvViewHolder holder, int position) {
        ReadingStaticRcvModel currentItem = newsItems.get(position);
        holder.newsImage.setImageResource(currentItem.getImage());
        holder.newsText.setText(currentItem.getText());


        if (check) {
            ArrayList<ReadingDynamicRcvModel> partItem = new ArrayList<>();
            partItem.add(new ReadingDynamicRcvModel("World", "#3FA0B5"));
            partItem.add(new ReadingDynamicRcvModel("Environment", "#3FA0B5"));
            partItem.add(new ReadingDynamicRcvModel("Society", "#3FA0B5"));
            partItem.add(new ReadingDynamicRcvModel("Politics", "#3FA0B5"));
            partItem.add(new ReadingDynamicRcvModel("Opinion", "#3FA0B5"));
            partItem.add(new ReadingDynamicRcvModel("Food Safety and Health", "#3FA0B5"));
            partItem.add(new ReadingDynamicRcvModel("Sports and Entertainment", "#3FA0B5"));
            partItem.add(new ReadingDynamicRcvModel("Travel and Cuisine", "#3FA0B5"));
            partItem.add(new ReadingDynamicRcvModel("Photo of the day", "#3FA0B5"));
            partItem.add(new ReadingDynamicRcvModel("Others", "#3FA0B5"));

            updateNewsRcv.callback(check_position, partItem);
            check = false;
        }
        if (check_position == position) {
            holder.newsSelectedLayout.setBackgroundResource(R.drawable.read_rcv_cat_selected);
            holder.newsText.setTextColor(Color.parseColor("#A18720"));
        } else {
            holder.newsSelectedLayout.setBackgroundResource(R.drawable.com_rcv_cat_bg);
            holder.newsText.setTextColor(Color.parseColor("#8F9193"));
        }
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public class ReadingStaticRcvViewHolder extends RecyclerView.ViewHolder {

        private final TextView newsText;
        private final ImageView newsImage;
        private final LinearLayout newsSelectedLayout;


        public ReadingStaticRcvViewHolder(@NonNull View itemView) {
            super(itemView);

            newsImage = itemView.findViewById(R.id.newsCatImage);
            newsText = itemView.findViewById(R.id.newsCatText);
            newsSelectedLayout = itemView.findViewById(R.id.raeding_rcv_linearlayout);

            //得到現在的item position
            newsSelectedLayout.setOnClickListener(v -> checkPosition(getAbsoluteAdapterPosition()));

        }

        private void checkPosition(int adapterPosition) {
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(check_position);
            check_position = adapterPosition;
            notifyItemChanged(check_position);


            //taiwan news
            if (check_position == 0) {//taiwan news
                ArrayList<ReadingDynamicRcvModel> partItem = new ArrayList<>();

                partItem.add(new ReadingDynamicRcvModel("World", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("Environment", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("Society", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("Politics", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("Opinion", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("Food Safety and Health", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("Sports and Entertainment", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("Travel and Cuisine", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("Photo of the day", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("Others", "#3FA0B5"));

                updateNewsRcv.callback(check_position, partItem);
                checkWhatNewsInterface.onNewsClicked(check_position);
            }
            //The New York Times
            else if (check_position == 1) {
                ArrayList<ReadingDynamicRcvModel> partItem = new ArrayList<>();
                partItem.add(new ReadingDynamicRcvModel("part", "#9EBD8B"));
                partItem.add(new ReadingDynamicRcvModel("part", "#9EBD8B"));
                partItem.add(new ReadingDynamicRcvModel("part", "#9EBD8B"));
                partItem.add(new ReadingDynamicRcvModel("part", "#9EBD8B"));
                partItem.add(new ReadingDynamicRcvModel("part", "#9EBD8B"));
                partItem.add(new ReadingDynamicRcvModel("part", "#9EBD8B"));
                updateNewsRcv.callback(check_position, partItem);
                checkWhatNewsInterface.onNewsClicked(check_position);
            }
            //BBC
            else if (check_position == 2) {
                ArrayList<ReadingDynamicRcvModel> partItem = new ArrayList<>();
                partItem.add(new ReadingDynamicRcvModel("part", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("part", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("part", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("part", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("part", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("part", "#3FA0B5"));
                updateNewsRcv.callback(check_position, partItem);
                checkWhatNewsInterface.onNewsClicked(check_position);

            }
            //CNN
            else if (check_position == 3) {
                ArrayList<ReadingDynamicRcvModel> partItem = new ArrayList<>();
                partItem.add(new ReadingDynamicRcvModel("part", "#9EBD8B"));
                partItem.add(new ReadingDynamicRcvModel("part", "#9EBD8B"));
                partItem.add(new ReadingDynamicRcvModel("part", "#9EBD8B"));
                partItem.add(new ReadingDynamicRcvModel("part", "#9EBD8B"));
                partItem.add(new ReadingDynamicRcvModel("part", "#9EBD8B"));
                partItem.add(new ReadingDynamicRcvModel("part", "#9EBD8B"));
                updateNewsRcv.callback(check_position, partItem);
                checkWhatNewsInterface.onNewsClicked(check_position);
            }
            //Huffpost
            else if (check_position == 4) {
                ArrayList<ReadingDynamicRcvModel> partItem = new ArrayList<>();
                partItem.add(new ReadingDynamicRcvModel("part", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("part", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("part", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("part", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("part", "#3FA0B5"));
                partItem.add(new ReadingDynamicRcvModel("part", "#3FA0B5"));
                updateNewsRcv.callback(check_position, partItem);
                checkWhatNewsInterface.onNewsClicked(check_position);

            }
        }

    }
}
