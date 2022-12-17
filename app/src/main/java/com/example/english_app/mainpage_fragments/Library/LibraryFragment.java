package com.example.english_app.mainpage_fragments.Library;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.english_app.R;
import com.example.english_app.mainpage_fragments.Library.Animal.NovelAnimalActivity;
import com.example.english_app.mainpage_fragments.Library.Child.NovelChildActivity;
import com.google.android.material.button.MaterialButton;


public class LibraryFragment extends Fragment {

    private MaterialButton latestBtn, childBtn, animalBtn;

    public LibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library, container, false);



        latestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NovelActivity.class);
            startActivity(intent);
        });
        childBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NovelChildActivity.class);
            startActivity(intent);
        });
        animalBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NovelAnimalActivity.class);
            startActivity(intent);
        });

        return view;
    }
}