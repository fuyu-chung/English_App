package com.example.english_app.mainpage_fragments.Library;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.english_app.R;
import com.google.android.material.button.MaterialButton;


public class LibraryFragment extends Fragment {

    private MaterialButton latestBtn, childBtn, animalBtn, literatureBtn;

    public LibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        latestBtn = view.findViewById(R.id.novelLatestBtn);
        childBtn = view.findViewById(R.id.novelChildBtn);
        animalBtn = view.findViewById(R.id.novelAnimalBtn);
        literatureBtn = view.findViewById(R.id.novelLiteratureBtn);


        latestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NovelLatestActivity.class);
            startActivity(intent);
        });
        childBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NovelChildActivity.class);
            startActivity(intent);
        });
        literatureBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NovelLiteratureActivity.class);
            startActivity(intent);
        });
        animalBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NovelAnimalActivity.class);
            startActivity(intent);
        });


        return view;
    }
}