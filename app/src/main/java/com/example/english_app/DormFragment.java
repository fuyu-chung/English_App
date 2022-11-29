package com.example.english_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class DormFragment extends Fragment {


    private ExtendedFloatingActionButton dormInfo;
    private Boolean isFABVisible;

    private ImageButton imgbtnCal, imgbtnProfile, imgbtnTask, imgbtnClock, imgbtnFriend, imgbtnNote, imgbtnColl;


//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    public DormFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment DormFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static DormFragment newInstance(String param1, String param2) {
//        DormFragment fragment = new DormFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            // TODO: Rename and change types of parameters
//            String mParam1 = getArguments().getString(ARG_PARAM1);
//            String mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

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
        Boolean isFabVisible = false;
        LinearLayout hintCal = view.findViewById(R.id.hint_cal);
        LinearLayout hintProfile = view.findViewById(R.id.hint_profile);
        LinearLayout hintTask = view.findViewById(R.id.hint_task);
        LinearLayout hintNote = view.findViewById(R.id.hint_note);
        LinearLayout hintColl = view.findViewById(R.id.hint_coll);
        LinearLayout hintWrong = view.findViewById(R.id.hint_wrong);
        LinearLayout hintClock = view.findViewById(R.id.hint_clock);
        LinearLayout hintFriend = view.findViewById(R.id.hint_friend);

        hintCal.setVisibility(View.INVISIBLE);
        hintProfile.setVisibility(View.INVISIBLE);
        hintTask.setVisibility(View.INVISIBLE);
        hintNote.setVisibility(View.INVISIBLE);
        hintColl.setVisibility(View.INVISIBLE);
        hintWrong.setVisibility(View.INVISIBLE);
        hintClock.setVisibility(View.INVISIBLE);
        hintFriend.setVisibility(View.INVISIBLE);

        //boolean as false
        isFabVisible = false;

        //dormInfo first shrink to circle
        dormInfo.shrink();

        dormInfo.setOnClickListener(v -> {

        });

        imgbtnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UserProfileActivity.class);
            startActivity(intent);
        });
        return view;
    }
}