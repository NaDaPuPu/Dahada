package com.d2w.dahada.data.activity_main.fragment_main.rex_ex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;

import java.util.Vector;

public class RecExerciseFragment4 extends Fragment {

    RecyclerView recyclerView;
    Vector<Movie> movies = new Vector<Movie>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rec_exercise_4, container, false);

        recyclerView = view.findViewById(R.id.rec_ex_4_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/MuFg23Tj7i0\" frameborder=\"0\" allowfullscreen></iframe>", "[한글자막] 마일리 사이러스 다리 운동 한글 자막 Miley Cyrus Workout") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Q70jQVPkMnc\" frameborder=\"0\" allowfullscreen></iframe>", "허벅지 안쪽살 빨리 빼는 운동 3가지") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/M0qtCqwVskU\" frameborder=\"0\" allowfullscreen></iframe>", "다리 얇아지는 최고의 운동 BEST5 [허벅지&종아리] THE 5 BEST SLIM LEG WORKOUT") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/BRO6-14-evE\" frameborder=\"0\" allowfullscreen></iframe>", "허벅지 바깥살 빨리 빼는 운동 3가지") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/6R_V7Yofudg\" frameborder=\"0\" allowfullscreen></iframe>", "무.조.건! 허벅지살 빠지는 운동") );


        RecEx_RecyclerAdapter recEx_recyclerAdapter = new RecEx_RecyclerAdapter(movies);

        recyclerView.setAdapter(recEx_recyclerAdapter);

        return view;
    }
}
