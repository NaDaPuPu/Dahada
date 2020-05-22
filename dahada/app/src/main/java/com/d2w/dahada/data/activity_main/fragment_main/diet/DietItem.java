package com.d2w.dahada.data.activity_main.fragment_main.diet;

public class DietItem {

    private String DietImage;
    private String DietName;
    private int DietKcal;

    public DietItem(String dietImage, String dietName, int dietKcal) {
        DietImage = dietImage;
        DietName = dietName;
        DietKcal = dietKcal;
    }

    public DietItem() {

    }


    public String getDietImage() {
        return DietImage;
    }

    public void setDietImage(String dietImage) {
        DietImage = dietImage;
    }

    public String getDietName() {
        return DietName;
    }

    public void setDietName(String dietName) {
        DietName = dietName;
    }

    public int getDietKcal() {
        return DietKcal;
    }

    public void setDietKcal(int dietKcal) {
        DietKcal = dietKcal;
    }
}
