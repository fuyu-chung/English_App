package com.example.english_app.user_dorm.collections;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;

import java.util.ArrayList;

public class CollectionRcvAdapter extends RecyclerView.Adapter<CollectionRcvAdapter.CollectionRcvViewHolder> {

    public ArrayList<CollectionRcvModel> collectionList;
    int check_position = -1;

    public CollectionRcvAdapter(ArrayList<CollectionRcvModel> collectionList){
        this.collectionList = collectionList;
    }

    public class CollectionRcvViewHolder extends RecyclerView.ViewHolder {

        public TextView vocText, chText;
        LinearLayout linearLayout;

        public CollectionRcvViewHolder(@NonNull View itemView) {
            super(itemView);

            vocText = itemView.findViewById(R.id.vocabulary);
            chText = itemView.findViewById(R.id.chinese);
            linearLayout = itemView.findViewById(R.id.vocabulary_wrongandcoll_linearlayout);
        }

        private void checkPosition(int adapterPosition) {
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(check_position);
            check_position = adapterPosition;
            notifyItemChanged(check_position);
        }

    }

    @NonNull
    @Override
    public CollectionRcvAdapter.CollectionRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vocabbulary_wrongandcoll_item, parent, false);
        return new CollectionRcvAdapter.CollectionRcvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionRcvAdapter.CollectionRcvViewHolder holder, int position) {
        CollectionRcvModel currentItem = collectionList.get(position);
        holder.vocText.setText(currentItem.getVocText());
        holder.chText.setText(currentItem.getChText());
    }

    @Override
    public int getItemCount() {
        return collectionList.size();
    }


}
