package com.d2w.dahada.data.activity_main.fragment_main.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.d2w.dahada.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecipeDetails2 extends AppCompatActivity {

    private ItemAdapter2 adapter;

    TextView rcpName,rcpKcal,rcpGram,rcpEx,rcpEx2;

    public RecipeDetails2() {
    }


    private ArrayList<RecipeItem2> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recipe_detail1);

        Intent intent = getIntent();

        rcpName = findViewById(R.id.rcpDetailname);
        rcpKcal = findViewById(R.id.rcpDetailkcal);
        rcpGram= findViewById(R.id.rcpDetailgram);
        rcpEx = findViewById(R.id.rcpDetailex1);
        rcpEx2 = findViewById(R.id.rcpDetailex2);


        int position = intent.getIntExtra("position", 0);
        Log.d("RecipeDetails", "position : " + position);

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("RecipeItem2/" + position); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RecipeItem2 recipeItem2 = dataSnapshot.getValue(RecipeItem2.class);
                assert recipeItem2 != null;
                rcpName.setText(recipeItem2.getRecipeName());
                rcpKcal.setText(recipeItem2.getRecipeKcal() + "kcal");
                rcpGram.setText(recipeItem2.getRecipeGram() + "g");
                rcpEx.setText(recipeItem2.getRecipeEx1());
                rcpEx2.setText(recipeItem2.getRecipeEx2());
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("RecipeDetails2", String.valueOf(databaseError.toException()));
            }

        });

    }
}