package com.d2w.dahada.data.activity_main.fragment_main.recipe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.d2w.dahada.R;


public class RecipeDetail1 extends Fragment {


    public RecipeDetail1() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail1, container, false);

        return view;
    }
}
