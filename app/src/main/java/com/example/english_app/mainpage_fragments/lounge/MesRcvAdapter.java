package com.example.english_app.mainpage_fragments.lounge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MesRcvAdapter extends RecyclerView.Adapter<MesRcvAdapter.MesRcvViewHolder> {

    public ArrayList<MesRcvModel> mesList;

    public void setData(ArrayList<MesRcvModel> mesList){
        this.mesList = mesList;
    }

    public class MesRcvViewHolder extends RecyclerView.ViewHolder {
        private TextView userName, userMsg, userMsgTime,userId;
        ConstraintLayout constraintLayout;

        public MesRcvViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            userId = itemView.findViewById(R.id.user_id);
            userMsg = itemView.findViewById(R.id.user_msg);
            userMsgTime = itemView.findViewById(R.id.user_msg_time);
            constraintLayout = itemView.findViewById(R.id.msgConstraintLayout);
        }
    }

    @NonNull
    @Override
    public MesRcvAdapter.MesRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new MesRcvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MesRcvViewHolder holder, int position) {
        MesRcvModel currentItem = mesList.get(position);
        holder.userName.setText(currentItem.getUserName());
        holder.userId.setText(String.valueOf(currentItem.getUserId()));
        holder.userMsg.setText(currentItem.getUserMsg());
        Date date = new Date(currentItem.getUerMsgTime().getTime());
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss", Locale.TAIWAN);
        holder.userMsgTime.setText(format.format(currentItem.getUerMsgTime()));
    }

    @Override
    public int getItemCount() {
        return mesList.size();
    }


}
