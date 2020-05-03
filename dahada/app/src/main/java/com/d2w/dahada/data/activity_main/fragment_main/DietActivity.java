package com.d2w.dahada.data.activity_main.fragment_main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.diet.FragmentDairy;
import com.d2w.dahada.data.activity_main.fragment_main.diet.FragmentMeat;
import com.d2w.dahada.data.activity_main.fragment_main.diet.FragmentNut;
import com.d2w.dahada.data.activity_main.fragment_main.diet.FragmentVeg;
import com.google.android.material.tabs.TabLayout;

public class DietActivity extends AppCompatActivity {
    FragmentVeg fragmentVeg;
    FragmentMeat fragmentMeat;
    FragmentDairy fragmentDairy;
    FragmentNut fragmentNut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_diet);

        fragmentVeg = new FragmentVeg();
        fragmentMeat = new FragmentMeat();
        fragmentDairy = new FragmentDairy();
        fragmentNut = new FragmentNut();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentVeg).commit();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("채소"));
        tabs.addTab(tabs.newTab().setText("고기"));
        tabs.addTab(tabs.newTab().setText("유제품"));
        tabs.addTab(tabs.newTab().setText("견과류"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0) {
                    selected = fragmentVeg;
                } else if (position == 1) {
                    selected = fragmentMeat;
                } else if (position == 2) {
                    selected = fragmentDairy;
                } else if (position == 3) {
                    selected = fragmentNut;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
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
