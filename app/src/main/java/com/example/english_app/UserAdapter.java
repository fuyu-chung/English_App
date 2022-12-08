package com.example.english_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    //list of user
    private List<User> mListUser;

    public void setData(List<User> list) {
        //現在這個list的data
        this.mListUser = list;
        notifyDataSetChanged();
    }


    //ViewHolder大致上和前端比較有關係，鍾妳應該用不到
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //這裡跟前端有關係，主要是資料要跟哪個XML做連接，這裡是和follower_item 的layout做連接
        //也和最下面有一個UserViewHolder class 有關
        //view holder holds the view that we're going to show
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.follower_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        //和前端印出文字有關
        User user = mListUser.get(position);
        if (user == null) {
            return;
        }
        //印出來的文字，這裡的getUserName連接到UserAdapter
        holder.userName.setText(user.getUserName());
        holder.userId.setText(String.valueOf("ID: " + user.getUserId()));
    }

    @Override
    public int getItemCount() {
        //數mListUser有幾項資料
        if (mListUser != null) {
            return mListUser.size();
        }
        return 0;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        private final TextView userName;
        private final TextView userId;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            userId = itemView.findViewById(R.id.user_id);

        }
    }
}
