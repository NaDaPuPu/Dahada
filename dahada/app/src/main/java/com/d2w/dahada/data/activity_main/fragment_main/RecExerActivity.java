package com.d2w.dahada.data.activity_main.fragment_main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.SectionPageAdapter;
import com.d2w.dahada.data.activity_main.fragment_main.rex_ex.RecEx_SectionPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class RecExerActivity extends AppCompatActivity {
    RecEx_SectionPageAdapter recEx_sectionPageAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_rec_exercise);

        TabLayout tabLayout = findViewById(R.id.rec_ex_tablayout);

        tabLayout.addTab((tabLayout.newTab().setText("전체")));
        tabLayout.addTab((tabLayout.newTab().setText("전신")));
        tabLayout.addTab((tabLayout.newTab().setText("상체")));
        tabLayout.addTab((tabLayout.newTab().setText("하체")));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        recEx_sectionPageAdapter = new RecEx_SectionPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager = findViewById(R.id.rec_ex_viewPager);
        viewPager.setAdapter(recEx_sectionPageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                recEx_sectionPageAdapter.notifyDataSetChanged();
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
