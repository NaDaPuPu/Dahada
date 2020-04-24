package com.d2w.dahada.data.bottombar;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import android.widget.CalendarView;

import android.widget.Toast;


import androidx.fragment.app.Fragment;
import com.d2w.dahada.R;

import com.d2w.dahada.R;

import java.util.Calendar;

public class Calender extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.calender, container, false);
    }
//프래그먼트 이동코드

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);



        //CalendarView 인스턴스 만들기

        CalendarView calendar = (CalendarView)findViewById(R.id.calendar);


    }

}
