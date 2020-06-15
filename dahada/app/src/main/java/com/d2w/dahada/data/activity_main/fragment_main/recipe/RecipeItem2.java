package com.d2w.dahada.data.activity_main.fragment_main.recipe;

public class RecipeItem2 {
    private String RecipeImage;
    private String RecipeName;
    private int RecipeKcal;
    private int RecipeGram;
    private String RecipeEx1;
    private String RecipeEx2;

    public RecipeItem2(String recipeImage, String recipeName, int recipeKcal, int recipeGram, String recipeEx1, String recipeEx2) {
        RecipeImage = recipeImage;
        RecipeName = recipeName;
        RecipeKcal = recipeKcal;
        RecipeGram = recipeGram;
        RecipeEx1 = recipeEx1;
        RecipeEx2 = recipeEx2;
    }

    public RecipeItem2() {

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

    public int getRecipeGram() {
        return RecipeGram;
    }

    public void setRecipeGram(int recipeGram) {
        RecipeGram = recipeGram;
    }

    public String getRecipeEx1() {
        return RecipeEx1;
    }

    public void setRecipeEx1(String recipeEx1) {
        RecipeEx1 = recipeEx1;
    }

    public String getRecipeEx2() {
        return RecipeEx2;
    }

    public void setRecipeEx2(String recipeEx2) {
        RecipeEx2 = recipeEx2;
    }
}
