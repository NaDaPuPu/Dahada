package com.d2w.dahada.data.activity_main.fragment_calendar;

import java.util.Date;

public class Schedule {
    private int kcal;
    private Date date;

    public Schedule(Date date, int kcal) {
        this.date = date;
        this.kcal = kcal;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
