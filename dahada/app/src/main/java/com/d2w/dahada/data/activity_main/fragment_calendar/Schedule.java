package com.d2w.dahada.data.activity_main.fragment_calendar;

import java.util.Date;

public class Schedule {
    private Date date;
    private int kcal;
    private String menu;

    public Schedule(Date date, int kcal, String menu) {
        this.date = date;
        this.kcal = kcal;
        this.menu = menu;
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
