package com.d2w.dahada.data.activity_main.fragment_main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.SectionPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class ShoppingActivity extends AppCompatActivity {
    SectionPageAdapter sectionPageAdapter;
    ViewPager shop_viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_shopping);

        TabLayout tabLayout = findViewById(R.id.shop_tablayout);

        tabLayout.addTab((tabLayout.newTab().setText("유제품")));
        tabLayout.addTab((tabLayout.newTab().setText("고기")));
        tabLayout.addTab((tabLayout.newTab().setText("채소")));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        shop_viewPager = findViewById(R.id.shop_viewPager);
        shop_viewPager.setAdapter(sectionPageAdapter);
        shop_viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                shop_viewPager.setCurrentItem(tab.getPosition());
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
