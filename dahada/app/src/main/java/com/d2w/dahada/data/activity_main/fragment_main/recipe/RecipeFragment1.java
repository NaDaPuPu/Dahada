package com.d2w.dahada.data.activity_main.fragment_main.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.MainActivity;
import com.d2w.dahada.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RecipeFragment1 extends Fragment {

    public RecipeFragment1(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.main_recipe_1, container, false);
        return view;

    }
}
