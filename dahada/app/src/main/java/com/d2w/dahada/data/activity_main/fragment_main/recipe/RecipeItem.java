package com.d2w.dahada.data.activity_main.fragment_main.recipe;

public class RecipeItem {
    private String picture;
    private String id;
    private int cal;

    public RecipeItem(String picture, String id, int cal) {
        this.picture = picture;
        this.id = id;
        this.cal = cal;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCal() {
        return cal;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }
}
