package com.d2w.dahada.data.activity_main.fragment_main.recipe;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionPageAdapter extends FragmentStatePagerAdapter {
    int num;
    public SectionPageAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RecipeFragment1 recipeFragment1 = new RecipeFragment1();
                return recipeFragment1;
            case 1:
                RecipeFragment2 recipeFragment2 = new RecipeFragment2();
                return recipeFragment2;
            case 2:
                RecipeFragment3 recipeFragment3 = new RecipeFragment3();
                return recipeFragment3;

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return num;
    }
}
