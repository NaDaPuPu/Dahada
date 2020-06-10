package com.d2w.dahada.data.activity_main.fragment_main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.RecipeFragment1;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.RecipeFragment2;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.RecipeFragment3;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.SectionPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class RecipeActivity extends AppCompatActivity {

    SectionPageAdapter sectionPageAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.main_recipe);

                TabLayout tabLayout = findViewById(R.id.tablayout);

                tabLayout.addTab((tabLayout.newTab().setText("식사용 식단")));
                tabLayout.addTab((tabLayout.newTab().setText("가벼운 식단")));
                tabLayout.addTab((tabLayout.newTab().setText("다이어트 간식")));
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

                viewPager = findViewById(R.id.viewPager);
                viewPager.setAdapter(sectionPageAdapter);
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                        sectionPageAdapter.notifyDataSetChanged();
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
