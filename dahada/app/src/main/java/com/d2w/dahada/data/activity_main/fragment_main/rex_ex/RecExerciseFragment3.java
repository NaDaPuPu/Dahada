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

public class RecExerciseFragment3 extends Fragment {

    RecyclerView recyclerView;
    Vector<Movie> movies = new Vector<Movie>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rec_exercise_3, container, false);

        recyclerView = view.findViewById(R.id.rec_ex_3_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/llwXGZlgdU0\" frameborder=\"0\" allowfullscreen></iframe>", "XHIT 레베카의 11자 복근운동 (7분)") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/iOSYLKBk894\" frameborder=\"0\" allowfullscreen></iframe>", "무.조.건! 뱃살 빠지는 운동 베스트5") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/jpTQdM7okkI\" frameborder=\"0\" allowfullscreen></iframe>", "티파니 10분 옆구리,허리 운동") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/54tTYO-vU2E\" frameborder=\"0\" allowfullscreen></iframe>", "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/jj6ze_eqmYI\" frameborder=\"0\" allowfullscreen></iframe>", "완벽한 복근을 만들기 위한 8분 루틴! 따라만하세요! (누구나 집에서도 가능) [8mins intense Abs Workout]") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/C3TqpGgnIoI\" frameborder=\"0\" allowfullscreen></iframe>", "상체 다이어트 운동 BEST [팔뚝살/겨드랑이살/가슴어깨/등살] UPPER BODY WORKOUT FOR WOMEN [ARM&ARMMPIT FAT]") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/2swcod5RYvU\" frameborder=\"0\" allowfullscreen></iframe>", "[PPIYAK's FIT]Ejercicio de la parte superior del cuerpo en casa집에서하는 상체운동!Upper body workout at home") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/spdpIgmqt8U\" frameborder=\"0\" allowfullscreen></iframe>", "상체운동(UPPER BODY) - 강하나 스트레칭(2018)") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/dDd1hVjqxEo\" frameborder=\"0\" allowfullscreen></iframe>", "초보자를 위한 하루 5분 복근 루틴 l 5 minutes abs workout") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7TLk7pscICk\" frameborder=\"0\" allowfullscreen></iframe>", "누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/hxjKZcOT17E\" frameborder=\"0\" allowfullscreen></iframe>", "10 Minute Ab Workout: How to Get a Six Pack") );



        RecEx_RecyclerAdapter recEx_recyclerAdapter = new RecEx_RecyclerAdapter(movies);

        recyclerView.setAdapter(recEx_recyclerAdapter);

        return view;
    }
}