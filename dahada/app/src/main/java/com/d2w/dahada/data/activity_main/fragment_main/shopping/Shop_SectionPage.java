package com.d2w.dahada.data.activity_main.fragment_main.shopping;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.d2w.dahada.data.activity_main.fragment_main.rex_ex.RecExerciseFragment1;
import com.d2w.dahada.data.activity_main.fragment_main.rex_ex.RecExerciseFragment2;
import com.d2w.dahada.data.activity_main.fragment_main.rex_ex.RecExerciseFragment3;
import com.d2w.dahada.data.activity_main.fragment_main.rex_ex.RecExerciseFragment4;

public class Shop_SectionPage extends FragmentStatePagerAdapter {


    int num;
    public Shop_SectionPage(@NonNull FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ShopFragment1 shopFragment1 = new ShopFragment1();
                return shopFragment1;
            case 1:
                ShopFragment2 shopFragment2 = new ShopFragment2();
                return shopFragment2;
            case 2:
                ShopFragment3 shopFragment3 = new ShopFragment3();
                return shopFragment3;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
