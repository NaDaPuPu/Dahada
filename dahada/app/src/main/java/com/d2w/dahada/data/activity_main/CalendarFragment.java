package com.d2w.dahada.data.activity_main;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_calendar.EventDecorator;
import com.d2w.dahada.data.activity_main.fragment_calendar.OneDayDecorator;
import com.d2w.dahada.data.activity_main.fragment_calendar.SaturdayDecorator;
import com.d2w.dahada.data.activity_main.fragment_calendar.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment {
    View v;
    private String kcal, menu;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    MaterialCalendarView materialCalendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_calender, container, false);

        materialCalendarView = v.findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 1, 1))
                .setMaximumDate(CalendarDay.from(2030, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator);

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth();
                int Day = date.getDay();

                String shot_Day = Year + "." + Month + "." + Day;

                materialCalendarView.clearSelection();

                Toast.makeText(getContext(), shot_Day, Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {
        String[] Time_Result;

        @Override
        protected List<CalendarDay> doInBackground(Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            for (int i = 0; i < Time_Result.length; i++) {
                CalendarDay calendarDay = CalendarDay.from(calendar);
                String[] time = Time_Result[i].split(".");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int day = Integer.parseInt(time[2]);

                dates.add(calendarDay);
                calendar.set(year, month - 1, day);
            }
            return dates;
        }

        @Override
        protected void onPostExecute(List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isRemoving()) {
                return;
            }

            materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, calendarDays, getActivity()));
        }
    }
}