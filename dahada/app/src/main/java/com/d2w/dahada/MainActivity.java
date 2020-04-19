package com.d2w.dahada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.d2w.dahada.data.bottombar.Calender;
import com.d2w.dahada.data.bottombar.Mypage;
import com.d2w.dahada.data.login.LoginActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Calender calender;
    Mypage mypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calender = new Calender();
        mypage = new Mypage();

        /*getSupportFragmentManager().beginTransaction().replace(R.id.container, calender).commit();*/

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab2:
                        Toast.makeText(getApplicationContext(), "캘린더 화면", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, calender).commit();

                        return true;

                    case R.id.tab5:
                        Toast.makeText(getApplicationContext(), "마이페이지", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mypage).commit();

                        return true;
                }
                return false;
            }
        });
//캘린더부분
        Button button = (Button) findViewById(R.id.toLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        Button button1 = findViewById(R.id.toHome);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), Home_DaeGeun.class);
                startActivity(intent);
            }
        });
    }
}

