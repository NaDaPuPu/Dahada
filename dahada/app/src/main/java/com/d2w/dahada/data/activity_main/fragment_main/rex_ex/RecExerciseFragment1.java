package com.d2w.dahada.data.activity_main.fragment_main.rex_ex;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.d2w.dahada.R;

import java.util.ArrayList;
import java.util.List;

public class RecExerciseFragment1 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rec_exercise_1, container, false);

        List<Movie> contents = new ArrayList<>();
        contents.add(new Movie("MuFg23Tj7i0", "마일리 사이러스 하체운동"));
        contents.add(new Movie("MuFg23Tj7i0", "마일리 사이러스 하체운동2"));

        recyclerView = view.findViewById(R.id.rec_ex_1_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new RecEx_RecyclerAdapter(contents));

        return view;
    }
}
