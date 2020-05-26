package com.d2w.dahada.data.activity_main.fragment_main.shopping;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.ItemAdapter;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.RecipeItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShopFragment1 extends Fragment {

    private ShopAdapter shopAdapter;

    public ShopFragment1() {
    }

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ShopItem> shopItemArrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_myshop_1, container, false);


        Log.d("test", "check2");
        recyclerView = view.findViewById(R.id.shop_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shopItemArrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        Log.d("test", "check3");
        databaseReference = database.getReference("ShopItem"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                shopItemArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ShopItem shopItem = snapshot.getValue(ShopItem.class);
                    shopItemArrayList.add(shopItem);
                }
                Log.d("TEST", String.valueOf(shopItemArrayList.size()));
                shopAdapter = new ShopAdapter(shopItemArrayList, getContext());
                recyclerView.setAdapter(shopAdapter);
                shopAdapter.notifyDataSetChanged();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("Fragment1", String.valueOf(databaseError.toException()));
            }

        });


        Log.d("test", "check5");

        return view;

    }
}
