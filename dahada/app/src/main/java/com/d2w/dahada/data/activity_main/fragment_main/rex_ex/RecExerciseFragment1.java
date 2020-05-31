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
import java.util.Vector;

public class RecExerciseFragment1 extends Fragment {

    RecyclerView recyclerView;
    Vector<Movie> movies = new Vector<Movie>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rec_exercise_1, container, false);

        recyclerView = view.findViewById(R.id.rec_ex_1_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eWEF1Zrmdow\" frameborder=\"0\" allowfullscreen></iframe>", "동영상1 제목을 적으세요.") );

        RecEx_RecyclerAdapter recEx_recyclerAdapter = new RecEx_RecyclerAdapter(movies);

        recyclerView.setAdapter(recEx_recyclerAdapter);

        return view;
    }
}
