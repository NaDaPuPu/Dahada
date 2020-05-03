package com.d2w.dahada.data.activity_main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.d2w.dahada.R;



public class Calender extends Fragment {


//프래그먼트 이동코드

    CalendarView calendarView;
    TextView myDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calender, container, false);

        calendarView = v.findViewById(R.id.CalendarView);
        myDate = v.findViewById(R.id.myDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int i, int i1, int i2) {
                String data = i + "/" + (i1 + 1) + "/" + i2;
                myDate.setText(data);
            }
        });

        return v;
    }
}
