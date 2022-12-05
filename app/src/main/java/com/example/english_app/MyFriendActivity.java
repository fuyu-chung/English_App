package com.example.english_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;

import com.example.english_app.friend_fragments.FollowerFragment;
import com.example.english_app.friend_fragments.FollowingFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MyFriendActivity extends AppCompatActivity {

    private TabLayout friendTablayout;
    private ViewPager friendViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friend);

        friendTablayout = findViewById(R.id.friend_tablayout);
        friendViewpager = findViewById(R.id.friend_viewpager);

        friendTablayout.setupWithViewPager(friendViewpager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new FollowerFragment(), "粉絲");
        vpAdapter.addFragment(new FollowingFragment(), "追蹤中");
        friendViewpager.setAdapter(vpAdapter);

    }



}