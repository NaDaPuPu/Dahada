package com.d2w.dahada.data.activity_main.fragment_main.diet;

public class DietItem_nut {

    private String DietImage_nut;
    private String DietName_nut;
    private int DietKcal_nut;

    public DietItem_nut() {

    }

    public String getDietImage_nut() {
        return DietImage_nut;
    }

    public void setDietImage_nut(String dietImage_nut) {
        DietImage_nut = dietImage_nut;
    }

    public String getDietName_nut() {
        return DietName_nut;
    }

    public void setDietName_nut(String dietName_nut) {
        DietName_nut = dietName_nut;
    }

    public int getDietKcal_nut() {
        return DietKcal_nut;
    }

    public void setDietKcal_nut(int dietKcal_nut) {
        DietKcal_nut = dietKcal_nut;
    }

    public DietItem_nut(String dietImage_nut, String dietName_nut, int dietKcal_nut) {
        DietImage_nut = dietImage_nut;
        DietName_nut = dietName_nut;
        DietKcal_nut = dietKcal_nut;
    }
}
