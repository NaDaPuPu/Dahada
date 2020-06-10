package com.d2w.dahada.data.activity_main.fragment_main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.SectionPageAdapter;
import com.d2w.dahada.data.activity_main.fragment_main.shopping.Shop_SectionPage;
import com.google.android.material.tabs.TabLayout;

public class ShoppingActivity extends AppCompatActivity {
    Shop_SectionPage shop_sectionPage;
    ViewPager shop_viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_shopping);

        TabLayout tabLayout = findViewById(R.id.shop_tablayout);

        tabLayout.addTab((tabLayout.newTab().setText("마켓")));
        tabLayout.addTab((tabLayout.newTab().setText("닭가슴살")));
        tabLayout.addTab((tabLayout.newTab().setText("샐러드")));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        shop_sectionPage = new Shop_SectionPage(getSupportFragmentManager(), tabLayout.getTabCount());

        shop_viewPager = findViewById(R.id.shop_viewPager);
        shop_viewPager.setAdapter(shop_sectionPage);
        shop_viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                shop_viewPager.setCurrentItem(tab.getPosition());
                shop_sectionPage.notifyDataSetChanged();
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
