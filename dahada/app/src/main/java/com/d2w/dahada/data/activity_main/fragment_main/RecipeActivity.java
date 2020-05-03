package com.d2w.dahada.data.activity_main.fragment_main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.d2w.dahada.R;
import com.google.android.material.tabs.TabLayout;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_recipe);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("식사용 식단"));
        tabs.addTab(tabs.newTab().setText("가벼운 식단"));
        tabs.addTab(tabs.newTab().setText("간식"));
        tabs.addTab(tabs.newTab().setText("비건 식단"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
