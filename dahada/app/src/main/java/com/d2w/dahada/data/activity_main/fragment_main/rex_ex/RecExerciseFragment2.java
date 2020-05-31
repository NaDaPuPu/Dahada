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
import java.util.Vector;

public class RecExerciseFragment2 extends Fragment {

    RecyclerView recyclerView;
    Vector<Movie> movies = new Vector<Movie>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rec_exercise_2, container, false);

        recyclerView = view.findViewById(R.id.rec_ex_2_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3VouSaW_LPw\" frameborder=\"0\" allowfullscreen></iframe>", "전신 다이어트 유산소운동 [홈트레이닝]") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/VNQpP6C1fJg\" frameborder=\"0\" allowfullscreen></iframe>", "집에서 하는 유산소운동 다이어트 [칼소폭] 30 MIN FULL BODY") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/swRNeYw1JkY\" frameborder=\"0\" allowfullscreen></iframe>", "하루 15분! 전신 칼로리 불태우는 다이어트 운동 15 MIN FULL BODY") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/MMswNnLdHso\" frameborder=\"0\" allowfullscreen></iframe>", "단기간 살빠지는 최고의 운동 [칼소폭2] 30 MIN FAT BURNING CARDIO WORKOUT") );


        RecEx_RecyclerAdapter recEx_recyclerAdapter = new RecEx_RecyclerAdapter(movies);

        recyclerView.setAdapter(recEx_recyclerAdapter);

        return view;
    }
}
