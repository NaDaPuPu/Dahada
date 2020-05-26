package com.d2w.dahada.data.activity_main.fragment_main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.SectionPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class RecExerActivity extends AppCompatActivity {
    SectionPageAdapter sectionPageAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_rec_exercise);

        TabLayout tabLayout = findViewById(R.id.rec_ex_tablayout);

        tabLayout.addTab((tabLayout.newTab().setText("전체")));
        tabLayout.addTab((tabLayout.newTab().setText("인기")));
        tabLayout.addTab((tabLayout.newTab().setText("전신")));
        tabLayout.addTab((tabLayout.newTab().setText("어깨/가슴")));
        tabLayout.addTab((tabLayout.newTab().setText("허리")));
        tabLayout.addTab((tabLayout.newTab().setText("복부")));
        tabLayout.addTab((tabLayout.newTab().setText("허벅지")));
        tabLayout.addTab((tabLayout.newTab().setText("종아리")));
        tabLayout.addTab((tabLayout.newTab().setText("체형교정")));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager = findViewById(R.id.rec_ex_viewPager);
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
