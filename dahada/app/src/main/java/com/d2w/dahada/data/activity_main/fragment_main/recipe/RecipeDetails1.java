package com.d2w.dahada.data.activity_main.fragment_main.recipe;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.shopping.ShopAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecipeDetails1 extends AppCompatActivity {

    private ItemAdapter adapter;

    public RecipeDetails1() {
    }


    private ArrayList<RecipeItem> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recipe_detail1);

        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        Log.d("test", "check3");
        databaseReference = database.getReference("RecipeItem"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    RecipeItem recipeItem = snapshot.getValue(RecipeItem.class);
                    Log.d("RecipeItem : ", recipeItem.getRecipeName());
                    Log.d("RecipeItem : ", recipeItem.getRecipeImage());
                    arrayList.add(recipeItem);
                }
                Log.d("TEST", String.valueOf(arrayList.size()));



            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("RecipeDetails1", String.valueOf(databaseError.toException()));
            }

        });

    }
}
