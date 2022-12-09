package com.example.english_app.colleges.vocab;

import android.content.Context;
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

public class StaticRcvAdapter extends RecyclerView.Adapter<StaticRcvAdapter.StaticRcvViewHolder>{

    private ArrayList<StaticRcvModel> catItems;
    int check_position = 0; //-1為no default，0為第一選項clicked
    private Context context;

    public StaticRcvAdapter(ArrayList<StaticRcvModel> catItems) {
        this.context = context;
        this.catItems = catItems;
    }

    @NonNull
    @Override
    public StaticRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rcv_item, parent, false);
        StaticRcvViewHolder staticRcvViewHolder = new StaticRcvViewHolder(view);
        return staticRcvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRcvViewHolder holder, final int position) {
        StaticRcvModel currentItem = catItems.get(position);
        holder.vocImage.setImageResource(currentItem.getImage());
        holder.vocText.setText(currentItem.getText());

//        holder.vocSelectedLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                check_position = position;
//                notifyDataSetChanged();
//            }
//        });

        if(check_position == position){
            holder.vocSelectedLayout.setBackgroundResource(R.drawable.voc_cat_rcv_selected);
        }else{
            holder.vocSelectedLayout.setBackgroundResource(R.drawable.voc_cat_rcv_bg);
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

            vocSelectedLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    checkPosition(getAbsoluteAdapterPosition());

                }
            });


        }
        private void checkPosition(int adapterPosition){
            if(adapterPosition == RecyclerView.NO_POSITION){
                return;
            }
            notifyItemChanged(check_position);
            check_position = adapterPosition;
            notifyItemChanged(check_position);
        }
        
    }
}
