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
import java.util.List;
import java.util.Random;

public class RecipeFragment1 extends Fragment {

    public RecipeFragment1(){

    }

    RecyclerView recyclerView;
    List<RecipeItem> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.main_recipe_1, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //initData();

        recyclerView.setAdapter(new ItemAdapter(initData()));

        return view;

    }
    private List<RecipeItem> initData() {

        itemList = new ArrayList<>();
        itemList.add(new RecipeItem(R.drawable.dahada, "레시피이름", 150, 300));
        itemList.add(new RecipeItem(R.drawable.dahada, "레시피이름", 150, 300));
        itemList.add(new RecipeItem(R.drawable.dahada, "레시피이름", 150, 300));
        itemList.add(new RecipeItem(R.drawable.dahada, "레시피이름", 150, 300));
        itemList.add(new RecipeItem(R.drawable.dahada, "레시피이름", 150, 300));
        itemList.add(new RecipeItem(R.drawable.dahada, "레시피이름", 150, 300));
        itemList.add(new RecipeItem(R.drawable.dahada, "레시피이름", 150, 300));
        itemList.add(new RecipeItem(R.drawable.dahada, "레시피이름", 150, 300));
        itemList.add(new RecipeItem(R.drawable.dahada, "레시피이름", 150, 300));
        itemList.add(new RecipeItem(R.drawable.dahada, "레시피이름", 150, 300));




        return itemList;
    }
}
