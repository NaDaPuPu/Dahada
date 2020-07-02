package com.d2w.dahada.data.activity_main.fragment_main.diet;

public class DietItem_dairy {

    private String DietImage_dairy;
    private String DietName_dairy;
    private int DietKcal_dairy;

    public DietItem_dairy() {

    }

    public String getDietImage_dairy() {
        return DietImage_dairy;
    }

    public void setDietImage_dairy(String dietImage_dairy) {
        DietImage_dairy = dietImage_dairy;
    }

    public String getDietName_dairy() {
        return DietName_dairy;
    }

    public void setDietName_dairy(String dietName_dairy) {
        DietName_dairy = dietName_dairy;
    }

    public int getDietKcal_dairy() {
        return DietKcal_dairy;
    }

    public void setDietKcal_dairy(int dietKcal_dairy) {
        DietKcal_dairy = dietKcal_dairy;
    }

    public DietItem_dairy(String dietImage_dairy, String dietName_dairy, int dietKcal_dairy) {
        DietImage_dairy = dietImage_dairy;
        DietName_dairy = dietName_dairy;
        DietKcal_dairy = dietKcal_dairy;
    }
}
