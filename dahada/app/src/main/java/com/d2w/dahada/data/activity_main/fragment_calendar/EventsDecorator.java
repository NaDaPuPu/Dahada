package com.d2w.dahada.data.activity_main.fragment_calendar;

import android.app.Activity;
import android.graphics.Color;

import com.d2w.dahada.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

public class EventsDecorator implements DayViewDecorator {
    private HashSet<CalendarDay> w_dates;
    public EventsDecorator(Collection<CalendarDay> w_dates, Activity context) {
        this.w_dates = new HashSet<>(w_dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return w_dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(8, Color.BLUE));
    }
}
