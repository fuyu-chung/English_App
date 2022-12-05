package com.example.english_app.friend_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.english_app.R;
import com.example.english_app.User;
import com.example.english_app.UserAdapter;

import java.util.ArrayList;
import java.util.List;


public class FollowerFragment extends Fragment {

    private RecyclerView rcvFollower;
    private View mView;

    public FollowerFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_follower, container, false);

        rcvFollower = mView.findViewById(R.id.rcv_follower);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvFollower.setLayoutManager(linearLayoutManager);

        UserAdapter userAdapter = new UserAdapter();
        userAdapter.setData(getListUser());

        rcvFollower.setAdapter(userAdapter);
        return mView;
    }

    private List<User> getListUser(){
        List<User> list = new ArrayList<>();
        list.add(new User("User name 1", "User ID 1"));
        list.add(new User("User name 2", "User ID 2"));
        list.add(new User("User name 3", "User ID 3"));
        list.add(new User("User name 4", "User ID 4"));
        list.add(new User("User name 5", "User ID 5"));
        list.add(new User("User name 6", "User ID 6"));
        list.add(new User("User name 7", "User ID 7"));
        list.add(new User("User name 1", "User ID 1"));
        list.add(new User("User name 2", "User ID 2"));
        list.add(new User("User name 3", "User ID 3"));
        list.add(new User("User name 4", "User ID 4"));
        list.add(new User("User name 5", "User ID 5"));
        list.add(new User("User name 6", "User ID 6"));
        list.add(new User("User name 7", "User ID 7"));
        list.add(new User("User name 1", "User ID 1"));
        list.add(new User("User name 2", "User ID 2"));
        list.add(new User("User name 3", "User ID 3"));
        list.add(new User("User name 4", "User ID 4"));
        list.add(new User("User name 5", "User ID 5"));
        list.add(new User("User name 6", "User ID 6"));
        list.add(new User("User name 7", "User ID 7"));
        list.add(new User("User name 1", "User ID 1"));
        list.add(new User("User name 2", "User ID 2"));
        list.add(new User("User name 3", "User ID 3"));
        list.add(new User("User name 4", "User ID 4"));
        list.add(new User("User name 5", "User ID 5"));
        list.add(new User("User name 6", "User ID 6"));
        list.add(new User("User name 7", "User ID 7"));

        return list;
    }
}