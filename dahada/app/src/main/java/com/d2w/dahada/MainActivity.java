package com.d2w.dahada;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.d2w.dahada.data.activity_main.Calender;
import com.d2w.dahada.data.activity_main.Community;
import com.d2w.dahada.data.activity_main.Main;
import com.d2w.dahada.data.activity_main.Mypage;
import com.d2w.dahada.data.activity_main.Notice;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    Main main;
    Calender calender;
    Community community;
    Notice notice;
    Mypage mypage;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId)
        {
            case R.id.menu_search:
                Toast.makeText(this, "검색 메뉴가 선택되었습니다", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        main = new Main();
        calender = new Calender();
        community = new Community();
        notice = new Notice();
        mypage = new Mypage();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, main).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
//                        Toast.makeText(getApplicationContext(), "홈", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, main).commit();
                        return true;

                    case R.id.tab2:
//                        Toast.makeText(getApplicationContext(), "캘린더 화면", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, calender).commit();
                        return true;

                    case R.id.tab3:
//                        Toast.makeText(getApplicationContext(), "커뮤니티", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, community).commit();
                        return true;

                    case R.id.tab4:
//                        Toast.makeText(getApplicationContext(), "알림", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, notice).commit();
                        return true;

                    case R.id.tab5:
//                        Toast.makeText(getApplicationContext(), "마이페이지", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mypage).commit();
                        return true;
                }
                return false;
            }
        });
    }
}

