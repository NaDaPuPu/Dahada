package com.d2w.dahada.data.bottombar;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import android.widget.CalendarView;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.d2w.dahada.R;

import com.d2w.dahada.R;

import java.util.Calendar;

public class Calender extends Fragment {


//프래그먼트 이동코드

    CalendarView calendarView;
    TextView myDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        calendarView = (CalendarView) getView().findViewById(R.id.CalendarView);
        myDate = (TextView) getView().findViewById(R.id.myDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int i, int i1, int i2) {
                String data = (i1 + 1) + "/" + i2 + "/" + i;
                myDate.setText(data);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.calender, container, false);
    }
}
