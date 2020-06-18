package com.d2w.dahada.data.activity_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.CommunityActivity;
import com.d2w.dahada.data.activity_main.fragment_main.DietActivity;
import com.d2w.dahada.data.activity_main.fragment_main.MapActivity;
import com.d2w.dahada.data.activity_main.fragment_main.RecipeActivity;
import com.d2w.dahada.data.activity_main.fragment_main.RecExerActivity;
import com.d2w.dahada.data.activity_main.fragment_main.ShoppingActivity;

public class Main extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        Button button_recipe = rootView.findViewById(R.id.button_recipe);
        Button button_diet = rootView.findViewById(R.id.button_diet);
        Button button_shopping = rootView.findViewById(R.id.button_shopping);
        Button button_exercise_map = rootView.findViewById(R.id.button_exercise_map);
        Button button_rec_exercise = rootView.findViewById(R.id.button_rec_exercise);
        Button button_circle = rootView.findViewById(R.id.button_circle);

        button_recipe.setOnClickListener(this);
        button_diet.setOnClickListener(this);
        button_shopping.setOnClickListener(this);
        button_exercise_map.setOnClickListener(this);
        button_rec_exercise.setOnClickListener(this);
        button_circle.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button_recipe:
                intent = new Intent(getActivity(), RecipeActivity.class);
                startActivity(intent);
                break;

            case R.id.button_diet:
                intent = new Intent(getActivity(), DietActivity.class);
                startActivity(intent);
                break;

            case R.id.button_shopping:
                intent = new Intent(getActivity(), ShoppingActivity.class);
                startActivity(intent);
                break;

            case R.id.button_exercise_map:
                intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
                break;

            case R.id.button_rec_exercise:
                intent = new Intent(getActivity(), RecExerActivity.class);
                startActivity(intent);
                break;

            case R.id.button_circle:
                intent = new Intent(getActivity(), CommunityActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}




