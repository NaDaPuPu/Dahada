package com.d2w.dahada.data.activity_main.fragment_mypage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_mypage.question.QuestionFragment1;
import com.d2w.dahada.data.activity_main.fragment_mypage.question.QuestionFragment2;
import com.google.android.material.tabs.TabLayout;

public class QuestionActivity extends AppCompatActivity {
    QuestionFragment1 questionFragment1;
    QuestionFragment2 questionFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionFragment1 = new QuestionFragment1();
        questionFragment2 = new QuestionFragment2();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, questionFragment1).commit();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("문의하기"));
        tabs.addTab(tabs.newTab().setText("문의내역확인"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0) {
                    selected = questionFragment1;
                } else if (position == 1) {
                    selected = questionFragment2;
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
