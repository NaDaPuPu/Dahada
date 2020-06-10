package com.d2w.dahada.data.activity_main.fragment_main.rex_ex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;

import java.util.ArrayList;
import java.util.List;

public class RecExerciseFragment2 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rec_exercise_2, container, false);

        List<Movie> contents = new ArrayList<>();
        contents.add(new Movie("swRNeYw1JkY", "하루 15분! 전신 칼로리 불태우는 다이어트 운동"));

        recyclerView = view.findViewById(R.id.rec_ex_2_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new RecEx_RecyclerAdapter(contents));

        return view;
    }
}
