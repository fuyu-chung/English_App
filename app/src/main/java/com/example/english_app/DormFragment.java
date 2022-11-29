package com.example.english_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DormFragment extends Fragment {


    private ExtendedFloatingActionButton dormInfo;
    private Boolean isFABVisible;

    private ImageButton imgbtnCal, imgbtnProfile, imgbtnTask, imgbtnClock, imgbtnFriend, imgbtnNote, imgbtnColl;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public DormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DormFragment newInstance(String param1, String param2) {
        DormFragment fragment = new DormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dorm, container, false);

        //all the variables
        dormInfo = view.findViewById(R.id.dorm_info);
        imgbtnCal = view.findViewById(R.id.imgbtn_cal);
        imgbtnProfile = view.findViewById(R.id.imgbtn_profile);
        imgbtnTask = view.findViewById(R.id.imgbtn_task);
        imgbtnClock = view.findViewById(R.id.imgbtn_clock);
        imgbtnFriend = view.findViewById(R.id.imgbtn_friend);
        imgbtnNote = view.findViewById(R.id.imgbtn_note);
        imgbtnColl = view.findViewById(R.id.imgbtn_coll);

        //write down all the gone texts

        //boolean as false
        isFABVisible = false;

        imgbtnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}