package com.d2w.dahada.data.activity_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.DietActivity;

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
        switch (view.getId()) {
            case R.id.button_recipe:
                Toast.makeText(getActivity(), "추천 레시피", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button_diet:
                Toast.makeText(getActivity(), "내 레시피", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DietActivity.class);
                startActivity(intent);
                break;

            case R.id.button_shopping:
                Toast.makeText(getActivity(), "쇼핑하기", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button_exercise_map:
                Toast.makeText(getActivity(), "내 운동코스", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button_rec_exercise:
                Toast.makeText(getActivity(), "추천 운동법", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button_circle:
                Toast.makeText(getActivity(), "공백", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}




