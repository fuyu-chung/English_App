package com.example.english_app.colleges.reading;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.colleges.competition.ComDynamicRcvAdapter;
import com.example.english_app.colleges.competition.ComDynamicRcvModel;

import java.util.ArrayList;

public class ReadingDynamicRcvAdapter extends RecyclerView.Adapter<ReadingDynamicRcvAdapter.ReadingDynamicRcvHolder> {
    private final RecyclerReadingViewInterface recyclerReadingViewInterface;
    int check_position = -1;

    public ArrayList<ReadingDynamicRcvModel> readingDynamicRcvModels;

    public ReadingDynamicRcvAdapter(ArrayList<ReadingDynamicRcvModel> readingDynamicRcvModels, RecyclerReadingViewInterface recyclerReadingViewInterface) {
        this.readingDynamicRcvModels = readingDynamicRcvModels;
        this.recyclerReadingViewInterface = recyclerReadingViewInterface;
    }

    public class ReadingDynamicRcvHolder extends RecyclerView.ViewHolder {
        public TextView partTextView;
        ConstraintLayout constraintLayout;

        public ReadingDynamicRcvHolder(@NonNull View itemView) {
            super(itemView);
            partTextView = itemView.findViewById(R.id.voc_rcv_unitText);
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

            if (recyclerReadingViewInterface != null) {
                recyclerReadingViewInterface.onPartItemClicked(check_position);
            }
        }

    }


    @NonNull
    @Override
    public ReadingDynamicRcvAdapter.ReadingDynamicRcvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rcv_item, parent, false);
        return new ReadingDynamicRcvAdapter.ReadingDynamicRcvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadingDynamicRcvAdapter.ReadingDynamicRcvHolder holder, int position) {
        ReadingDynamicRcvModel currentItem = readingDynamicRcvModels.get(position);
        holder.partTextView.setText(currentItem.getUnitText());
        holder.partTextView.setTextColor(Color.parseColor(currentItem.getColor()));//改變顏色

        holder.constraintLayout.setBackgroundResource(R.drawable.voc_unit_rcv_bg);
    }

    @Override
    public int getItemCount() {
        return readingDynamicRcvModels.size();
    }


}
