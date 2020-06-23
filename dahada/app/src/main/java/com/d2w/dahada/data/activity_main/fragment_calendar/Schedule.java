package com.d2w.dahada.data.activity_main.fragment_calendar;

import java.util.Date;

public class Schedule {
    private Date date;
    private int kcal;
    private String menu;
    private int water;

    public Schedule(Date date, int kcal, String menu, int water) {
        this.date = date;
        this.kcal = kcal;
        this.menu = menu;
        this.water = water;
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

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }
}
