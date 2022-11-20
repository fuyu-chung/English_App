package com.example.english_app;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollegeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollegeFragment extends Fragment {

    //must be private
    //01=vocabulary, 02=phrase, 03=reading, 04=competition
    private CardView cardView01, cardView02, cardView03, cardView04;
    private TextView detailText01, detailText02, detailText03, detailText04;
    private RelativeLayout relativeLayout01, relativeLayout02, relativeLayout03, relativeLayout04;
    private TextView extendText01, extendText02, extendText03, extendText04;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CollegeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CollegeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CollegeFragment newInstance(String param1, String param2) {
        CollegeFragment fragment = new CollegeFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_college, container, false);


        cardView01 = view.findViewById(R.id.cardViewCollege);
        detailText01 = view.findViewById(R.id.detailCollegeText);
        relativeLayout01 = view.findViewById(R.id.collegeLayout01);
        extendText01 = view.findViewById(R.id.extendCollegeLayout01Text);


        cardView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int var = (detailText01.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
                int var2 = (extendText01.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
                TransitionManager.beginDelayedTransition(relativeLayout01, new AutoTransition());
                detailText01.setVisibility(var);
                extendText01.setVisibility(var2);

            }
        });

        cardView02 = view.findViewById(R.id.cardViewPhrase);
        detailText02 = view.findViewById(R.id.detailPhraseText);
        relativeLayout02 = view.findViewById(R.id.collegeLayout02);
        extendText02 = view.findViewById(R.id.extendCollegeLayout02Text);

        cardView02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int var = (detailText02.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
                int var2 = (extendText02.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
                TransitionManager.beginDelayedTransition(relativeLayout02, new AutoTransition());
                detailText02.setVisibility(var);
                extendText02.setVisibility(var2);
            }
        });

        cardView03 = view.findViewById(R.id.cardViewReading);
        detailText03 = view.findViewById(R.id.detailReadingText);
        relativeLayout03 = view.findViewById(R.id.collegeLayout03);
        extendText03 = view.findViewById(R.id.extendCollegeLayout03Text);

        cardView03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int var = (detailText03.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
                int var2 = (extendText03.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
                TransitionManager.beginDelayedTransition(relativeLayout03, new AutoTransition());
                detailText03.setVisibility(var);
                extendText03.setVisibility(var2);

            }
        });

        cardView04 = view.findViewById(R.id.cardViewCompete);
        detailText04 = view.findViewById(R.id.detailCompeteText);
        relativeLayout04 = view.findViewById(R.id.collegeLayout04);
        extendText04 = view.findViewById(R.id.extendCollegeLayout04Text);

        cardView04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int var = (detailText04.getVisibility() == View.VISIBLE)? View.GONE : View.VISIBLE;
                int var2 = (extendText04.getVisibility() == View.VISIBLE)? View.GONE : View.VISIBLE;
                TransitionManager.beginDelayedTransition(relativeLayout04, new AutoTransition());
                detailText04.setVisibility(var);
                extendText04.setVisibility(var2);
            }
        });


        return view;
    }


}