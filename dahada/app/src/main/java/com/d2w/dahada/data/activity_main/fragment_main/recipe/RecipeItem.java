package com.d2w.dahada.data.activity_main.fragment_main.recipe;

public class RecipeItem {
    private int recipe_Image;
    private String recipe_name;
    private int recipe_gram;
    private int recipe_kcal;

    public RecipeItem(int recipe_Image, String recipe_name, int recipe_gram, int recipe_kcal) {
        this.recipe_Image = recipe_Image;
        this.recipe_name = recipe_name;
        this.recipe_gram = recipe_gram;
        this.recipe_kcal = recipe_kcal;
    }

    public int getRecipe_Image() {
        return recipe_Image;
    }

    public void setRecipe_Image(int recipe_Image) {
        this.recipe_Image = recipe_Image;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public int getRecipe_gram() {
        return recipe_gram;
    }

    public void setRecipe_gram(int recipe_gram) {
        this.recipe_gram = recipe_gram;
    }

    public int getRecipe_kcal() {
        return recipe_kcal;
    }

    public void setRecipe_kcal(int recipe_kcal) {
        this.recipe_kcal = recipe_kcal;
    }
}
