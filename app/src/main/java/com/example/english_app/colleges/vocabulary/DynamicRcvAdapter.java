package com.example.english_app.colleges.vocabulary;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.colleges.vocabulary.VRcvInterface.RecyclerViewInterface;

import java.util.ArrayList;

public class DynamicRcvAdapter extends RecyclerView.Adapter<DynamicRcvAdapter.DynamicRcvHolder>{

    private final RecyclerViewInterface recyclerViewInterface;
    int check_position = -1;
    boolean check = true;

    public ArrayList<DynamicRcvModel> dynamicRcvModels;

    public DynamicRcvAdapter(ArrayList<DynamicRcvModel> dynamicRcvModels,RecyclerViewInterface recyclerViewInterface){
        this.dynamicRcvModels = dynamicRcvModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public class DynamicRcvHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        ConstraintLayout constraintLayout;


        public DynamicRcvHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.voc_rcv_unitText);
            constraintLayout = itemView.findViewById(R.id.voc_unit_layout);


            constraintLayout.setOnClickListener(v -> checkPosition(getAbsoluteAdapterPosition()));


        }
        private void checkPosition(int adapterPosition){
            check_position=-1;
            System.out.println(check_position);
            System.out.println(adapterPosition);

            if(adapterPosition == RecyclerView.NO_POSITION){
                return;
            }
            notifyItemChanged(check_position);
            check_position = adapterPosition;
            notifyItemChanged(check_position);

            System.out.println(check_position);
            System.out.println(adapterPosition);

            if(recyclerViewInterface != null){
                recyclerViewInterface.onItemClicked(check_position);
            }


        }
    }

    @NonNull
    @Override
    public DynamicRcvAdapter.DynamicRcvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rcv_item, parent, false);
        return new DynamicRcvHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRcvAdapter.DynamicRcvHolder holder, int position) {
        DynamicRcvModel currentItem = dynamicRcvModels.get(position);
        holder.textView.setText(currentItem.getUnitText());
        holder.textView.setTextColor(Color.parseColor(currentItem.getColor()));//改變顏色
        holder.constraintLayout.setBackgroundResource(R.drawable.voc_unit_rcv_bg);

    }

    @Override
    public int getItemCount() {
        return dynamicRcvModels.size();
    }


}

