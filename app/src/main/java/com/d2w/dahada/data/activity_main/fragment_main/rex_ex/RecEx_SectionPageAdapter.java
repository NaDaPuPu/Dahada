package com.d2w.dahada.data.activity_main.fragment_main.rex_ex;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class RecEx_SectionPageAdapter extends FragmentStatePagerAdapter {
    int num;
    public RecEx_SectionPageAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RecExerciseFragment1 recExerciseFragment1 = new RecExerciseFragment1();
                return recExerciseFragment1;
            case 1:
                RecExerciseFragment2 recExerciseFragment2 = new RecExerciseFragment2();
                return recExerciseFragment2;
            case 2:
                RecExerciseFragment3 recExerciseFragment3 = new RecExerciseFragment3();
                return recExerciseFragment3;
            case 3:
                RecExerciseFragment4 recExerciseFragment4 = new RecExerciseFragment4();
                return recExerciseFragment4;

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return num;
    }
}
