package com.d2w.dahada.data.activity_main.fragment_main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.d2w.dahada.MainActivity;
import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.diet.DietItem;
import com.d2w.dahada.data.activity_main.fragment_main.diet.FragmentDairy;
import com.d2w.dahada.data.activity_main.fragment_main.diet.FragmentMeat;
import com.d2w.dahada.data.activity_main.fragment_main.diet.FragmentNut;
import com.d2w.dahada.data.activity_main.fragment_main.diet.FragmentVeg;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.RecipeItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DietActivity extends AppCompatActivity {
    FragmentVeg fragmentVeg;
    FragmentMeat fragmentMeat;
    FragmentDairy fragmentDairy;
    FragmentNut fragmentNut;

    EditText editText;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    EditText et_show,et_result;

    Button add,sub,mul,div,remainder,del;
    Button result;

    //이번 연산의 결과를 저장
    String history = "";
    //피연산자1
    String number1 = "";
    //피연산자2
    String number2 = "";

    //어떤 연산자가 선택되었는지 확인하기 위한 int형 type변수
    int type;

    int ADD = 0;
    int SUB = 1;
    int MUL = 2;
    int DIV = 3;
    int REMAINDER = 4;
    double d1;
    double d2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_diet);

        fragmentVeg = new FragmentVeg();
        fragmentMeat = new FragmentMeat();
        fragmentDairy = new FragmentDairy();
        fragmentNut = new FragmentNut();

        et_show = findViewById(R.id.et_show);
        et_result = findViewById(R.id.et_result);
        et_result.setText("");
        add = findViewById(R.id.btn_add);
        sub = findViewById(R.id.btn_sub);
        mul = findViewById(R.id.btn_mul);
        div = findViewById(R.id.btn_div);
        remainder = findViewById(R.id.btn_remainder);
        del = findViewById(R.id.btn_del);

        result = findViewById(R.id.btn_result);

        add.setOnClickListener(mListener);
        sub.setOnClickListener(mListener);
        mul.setOnClickListener(mListener);
        div.setOnClickListener(mListener);
        remainder.setOnClickListener(mListener);
        result.setOnClickListener(mListener);
        del.setOnClickListener(mListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentVeg).commit();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("채소"));
        tabs.addTab(tabs.newTab().setText("고기"));
        tabs.addTab(tabs.newTab().setText("유제품"));
        tabs.addTab(tabs.newTab().setText("견과류"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0) {
                    selected = fragmentVeg;
                } else if (position == 1) {
                    selected = fragmentMeat;
                } else if (position == 2) {
                    selected = fragmentDairy;
                } else if (position == 3) {
                    selected = fragmentNut;
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

        Intent intent = getIntent();

        editText = (EditText)findViewById(R.id.edit);

        final int position = intent.getIntExtra("position", 0);
        Log.d("DietItem", "position : " + position);

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("DietItem/" + position); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DietItem dietItem = dataSnapshot.getValue(DietItem.class);
                editText.setText(position + "");
                assert dietItem != null;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DietItem", String.valueOf(databaseError.toException()));
            }

        });

        Button clear = findViewById(R.id.btn_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_show.setText("");
                et_result.setText("");
                d1 = d2 = 0;
                history = number1 = number2 = "";
            }
        });

        Button plus_minus = findViewById(R.id.btn_plus_minus);
        plus_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //실수인지 정수인지 판단해서 부호 바꾸기
                if( ( (Double.parseDouble(et_result.getText().toString())) - ((int)Double.parseDouble(et_result.getText().toString())) ) == 0.0 )
                {
                    et_result.setText( "" + (Integer.parseInt(et_result.getText().toString()) * -1) );
                }
                else {
                    et_result.setText( "" + (Double.parseDouble(et_result.getText().toString()) * -1) );
                }

            }
        });
    }

    Button.OnClickListener mListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(et_result.getText().toString() == null)
            {
                Toast.makeText(DietActivity.this,"수를 입력하세요",Toast.LENGTH_SHORT).show();
            }
            switch (v.getId()) {
                case R.id.btn_add :
                    number1 = et_result.getText().toString();
                    history = et_result.getText().toString() + " + ";
                    et_show.setText(history);
                    et_result.setText("");

                    type = ADD;
                    break;

                case R.id.btn_sub :
                    number1 = et_result.getText().toString();
                    history = et_result.getText().toString() + " - ";
                    et_show.setText(history);
                    et_result.setText("");

                    type = SUB;
                    break;

                case R.id.btn_mul :
                    number1 = et_result.getText().toString();
                    history = et_result.getText().toString() + " * ";
                    et_show.setText(history);
                    et_result.setText("");

                    type = MUL;
                    break;

                case R.id.btn_div :
                    number1 = et_result.getText().toString();
                    history = et_result.getText().toString() + " / ";
                    et_show.setText(history);
                    et_result.setText("");

                    type = DIV;
                    break;

                case R.id.btn_remainder :
                    number1 = et_result.getText().toString();
                    history = et_result.getText().toString() + " % ";
                    et_show.setText(history);
                    et_result.setText("");

                    type = REMAINDER;
                    break;

                case R.id.btn_del :

                    String del_number = et_result.getText().toString();
                    Toast.makeText(DietActivity.this,del_number,Toast.LENGTH_SHORT).show();



                    et_result.setText(del_number.substring(0,del_number.length() - 1));
                    break;

                case R.id.btn_result :
                    double result = 0;
                    Toast.makeText(DietActivity.this, "결과", Toast.LENGTH_SHORT).show();
                    number2 = et_result.getText().toString();
                    history = history + et_result.getText().toString();
                    et_show.setText(history);

                    d1 = Double.parseDouble(number1);
                    d2 = Double.parseDouble(number2);

                    if(type == ADD) {
                        result = d1 + d2;
                        et_result.setText("" + result);
                    } else if (type == SUB) {
                        result = d1 - d2;
                        et_result.setText("" + result);
                    }else if (type == MUL) {
                        result = d1 * d2;
                        et_result.setText("" + result);
                    }else if (type == DIV) {
                        result = d1 / d2;
                        et_result.setText("" + result);
                    }else if (type == REMAINDER) {
                        result = d1 % d2;
                        et_result.setText("" + result);
                    }

                    number1 = et_result.getText().toString();
                    break;
            }
        }
    };

    public void onClick (View v)
    {
        switch(v.getId()){
            case R.id.btn0 : et_result.setText(et_result.getText().toString() + 0); break;
            case R.id.btn1 : et_result.setText(et_result.getText().toString() + 1); break;
            case R.id.btn2 : et_result.setText(et_result.getText().toString() + 2); break;
            case R.id.btn3 : et_result.setText(et_result.getText().toString() + 3); break;
            case R.id.btn4 : et_result.setText(et_result.getText().toString() + 4); break;
            case R.id.btn5 : et_result.setText(et_result.getText().toString() + 5); break;
            case R.id.btn6 : et_result.setText(et_result.getText().toString() + 6); break;
            case R.id.btn7 : et_result.setText(et_result.getText().toString() + 7); break;
            case R.id.btn8 : et_result.setText(et_result.getText().toString() + 8); break;
            case R.id.btn9 : et_result.setText(et_result.getText().toString() + 9); break;
            case R.id.btndot : et_result.setText(et_result.getText().toString() + "."); break;
        }


    }
}
