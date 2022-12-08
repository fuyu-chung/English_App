package com.example.english_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.mainpage_fragments.Following;

import java.util.List;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder> {
    //list of Following
    private List<Following> mListFollowing;

    public void setData(List<User> list) {
        //現在這個list的data
        this.mListFollowing = list;
        notifyDataSetChanged();
    }


    //ViewHolder大致上和前端比較有關係，鍾妳應該用不到
    @NonNull
    @Override
    public FollowingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //這裡跟前端有關係，主要是資料要跟哪個XML做連接，這裡是和follower_item 的layout做連接
        //也和最下面有一個UserViewHolder class 有關
        //view holder holds the view that we're going to show
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.following_item, parent, false);
        return new FollowingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingViewHolder holder, int position) {
        //和前端印出文字有關
        Following following = mListFollowing.get(position);
        if (following == null) {
            return;
        }
        //印出來的文字，這裡的getUserName連接到UserAdapter
        holder.userName.setText(following.getUserName());
        holder.userId.setText(String.valueOf("ID: "+ following.getUserId()));
    }

    @Override
    public int getItemCount() {
        //數mListUser有幾項資料
        if (mListFollowing != null) {
            return mListFollowing.size();
        }
        return 0;
    }

    public static class FollowingViewHolder extends RecyclerView.ViewHolder {

        private final TextView userName;
        private final TextView userId;

        public FollowingViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            userId = itemView.findViewById(R.id.user_id);

        }
    }
}
