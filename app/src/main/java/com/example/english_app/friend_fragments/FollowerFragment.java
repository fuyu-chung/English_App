package com.example.english_app.friend_fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.User;
import com.example.english_app.UserAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FollowerFragment extends Fragment {

    //連接userAdapter 先宣告變數
    private RecyclerView rcvFollower;
    //跟fragment 有關的view 宣告
    private View mView;

    public FollowerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_follower, container, false);

        //因為我的recycler view 在linear layout 裡面，所以它去裡面找
        rcvFollower = mView.findViewById(R.id.rcv_follower);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvFollower.setLayoutManager(linearLayoutManager);
        UserAdapter userAdapter = new UserAdapter();
        //這裡呼叫UserAdapter裡面的setData class
        userAdapter.setData(getListFollower());
        rcvFollower.setAdapter(userAdapter);
        return mView;
    }

    private List<User> getListFollower() {
        List<User> list = new ArrayList<>();
        List<User> temp = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try {
                SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("User", MODE_PRIVATE);
                int id = sharedPreferences.getInt("user_id", 0);
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                String query = "select friends.user_id, account.user_name from friends, account where friends.user_id = account.user_id AND friends.friend_id = ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    list.add(new User(resultSet.getInt(1), resultSet.getString(2)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        temp = list;
        return temp;
    }
    public void onStart(){
        super.onStart();
        Executors.newSingleThreadExecutor();
    }

}