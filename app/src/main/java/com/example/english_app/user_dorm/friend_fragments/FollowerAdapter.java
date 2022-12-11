package com.example.english_app.user_dorm.friend_fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;

import java.util.List;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder> {
    //list of user
    private List<Follower> mListFollower;

    public void setData(List<Follower> list) {
        //現在這個list的data
        this.mListFollower = list;
//        notifyDataSetChanged();
    }


    //ViewHolder大致上和前端比較有關係，鍾妳應該用不到
    @NonNull
    @Override
    public FollowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //這裡跟前端有關係，主要是資料要跟哪個XML做連接，這裡是和follower_item 的layout做連接
        //也和最下面有一個UserViewHolder class 有關
        //view holder holds the view that we're going to show
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.follower_item, parent, false);
        return new FollowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowerViewHolder holder, int position) {
        //和前端印出文字有關
        Follower follower = mListFollower.get(position);
        if (follower == null) {
            return;
        }
        //印出來的文字，這裡的getUserName連接到UserAdapter
        holder.userName.setText(follower.getUserName());
        String ID = "ID" + follower.getUserId();
        holder.userId.setText(ID);
    }

    @Override
    public int getItemCount() {
        //數mListUser有幾項資料
        if (mListFollower != null) {
            return mListFollower.size();
        }
        return 0;
    }

    public static class FollowerViewHolder extends RecyclerView.ViewHolder {

        private final TextView userName;
        private final TextView userId;

        public FollowerViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            userId = itemView.findViewById(R.id.user_id);

        }
    }
}
