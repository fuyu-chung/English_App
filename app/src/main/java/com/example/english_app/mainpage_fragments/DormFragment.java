package com.example.english_app.mainpage_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.english_app.MyFriendActivity;
import com.example.english_app.R;
import com.example.english_app.user_basic.UserProfileActivity;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class DormFragment extends Fragment {


    private ExtendedFloatingActionButton dormInfo;
    private Boolean isFABVisible;

    private ImageButton imgbtnCal, imgbtnProfile, imgbtnTask, imgbtnClock, imgbtnFriend, imgbtnNote, imgbtnColl;


    public DormFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dorm, container, false);

        //all the variables of img BTN
        ExtendedFloatingActionButton dormInfo = view.findViewById(R.id.dorm_info);
        ImageButton imgbtnCal = view.findViewById(R.id.imgbtn_cal);
        ImageButton imgbtnProfile = view.findViewById(R.id.imgbtn_profile);
        ImageButton imgbtnTask = view.findViewById(R.id.imgbtn_task);
        ImageButton imgbtnClock = view.findViewById(R.id.imgbtn_clock);
        ImageButton imgbtnFriend = view.findViewById(R.id.imgbtn_friend);
        ImageButton imgbtnNote = view.findViewById(R.id.imgbtn_note);
        ImageButton imgbtnColl = view.findViewById(R.id.imgbtn_coll);
        ImageButton imgbtnWrong = view.findViewById(R.id.imgbtn_wrong);
        imgbtnClock = view.findViewById(R.id.imgbtn_clock);

        //write down all the invisible layout
        final boolean[] isFabVisible = new boolean[1];
        LinearLayout hintCal = view.findViewById(R.id.hint_cal);
        LinearLayout hintProfile = view.findViewById(R.id.hint_profile);
        LinearLayout hintTask = view.findViewById(R.id.hint_task);
        LinearLayout hintNote = view.findViewById(R.id.hint_note);
        LinearLayout hintColl = view.findViewById(R.id.hint_coll);
        LinearLayout hintWrong = view.findViewById(R.id.hint_wrong);
        LinearLayout hintClock = view.findViewById(R.id.hint_clock);
        LinearLayout hintFriend = view.findViewById(R.id.hint_friend);


        //boolean as false
        isFabVisible[0] = false;

        //dormInfo first shrink to circle
        dormInfo.shrink();

        dormInfo.setOnClickListener(v -> {
            if(!isFabVisible[0]){
                hintCal.setVisibility(View.VISIBLE);
                hintProfile.setVisibility(View.VISIBLE);
                hintTask.setVisibility(View.VISIBLE);
                hintNote.setVisibility(View.VISIBLE);
                hintColl.setVisibility(View.VISIBLE);
                hintWrong.setVisibility(View.VISIBLE);
                hintClock.setVisibility(View.VISIBLE);
                hintFriend.setVisibility(View.VISIBLE);

                dormInfo.extend();

                isFabVisible[0] = true;
            }else{
                hintCal.setVisibility(View.INVISIBLE);
                hintProfile.setVisibility(View.INVISIBLE);
                hintTask.setVisibility(View.INVISIBLE);
                hintNote.setVisibility(View.INVISIBLE);
                hintColl.setVisibility(View.INVISIBLE);
                hintWrong.setVisibility(View.INVISIBLE);
                hintClock.setVisibility(View.INVISIBLE);
                hintFriend.setVisibility(View.INVISIBLE);

                dormInfo.shrink();

                isFabVisible[0] = false;
            }
        });

        imgbtnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UserProfileActivity.class);
            startActivity(intent);
        });
        imgbtnFriend.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MyFriendActivity.class);
            startActivity(intent);
        });
        return view;
    }
}