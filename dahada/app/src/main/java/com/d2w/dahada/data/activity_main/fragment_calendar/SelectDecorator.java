package com.d2w.dahada.data.activity_main.fragment_calendar;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.d2w.dahada.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import io.opencensus.trace.Span;

public class SelectDecorator implements DayViewDecorator {
    private final Drawable drawable;
    private CalendarDay date;

    public SelectDecorator(CalendarDay selectedDate , Activity context) {
        date = selectedDate;
        drawable = context.getResources().getDrawable(R.drawable.circledrawable);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new StyleSpan(Typeface.BOLD));
        view.addSpan(new ForegroundColorSpan(Color.WHITE));
        view.addSpan(new RelativeSizeSpan(1.4f));
        view.setBackgroundDrawable(drawable);
        //view.setSelectionDrawable(drawable);
    }
}
