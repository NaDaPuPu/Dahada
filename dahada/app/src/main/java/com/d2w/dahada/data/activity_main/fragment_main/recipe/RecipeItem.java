package com.d2w.dahada.data.activity_main.fragment_main.recipe;

public class RecipeItem {
    private String RecipeImage;
    private String RecipeName;
    private int RecipeKcal;

    public RecipeItem(String recipeImage, String recipeName, int recipeKcal) {
        RecipeImage = recipeImage;
        RecipeName = recipeName;
        RecipeKcal = recipeKcal;
    }

    public RecipeItem() {

    }

    public String getRecipeImage() {
        return RecipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        RecipeImage = recipeImage;
    }

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    public int getRecipeKcal() {
        return RecipeKcal;
    }

    public void setRecipeKcal(int recipeKcal) {
        RecipeKcal = recipeKcal;
    }
}
