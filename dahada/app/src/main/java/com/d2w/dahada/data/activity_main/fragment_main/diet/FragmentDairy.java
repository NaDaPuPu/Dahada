package com.d2w.dahada.data.activity_main.fragment_main.diet;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentDairy extends Fragment {

    private DietAdapter_dairy adapter_dairy;

    public FragmentDairy() {
    }
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<DietItem_dairy> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_dairy, container, false);

        Log.d("test","check2");
        recyclerView = view.findViewById(R.id.recyclerview_diet_dairy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        Log.d("test","check3");
        databaseReference = database.getReference("DietItem_dairy"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DietItem_dairy dietItem_dairy = snapshot.getValue(DietItem_dairy.class);
                    arrayList.add(dietItem_dairy);
                }
                Log.d("TEST",String.valueOf(arrayList.size()));
                adapter_dairy = new DietAdapter_dairy(arrayList,getContext());
                recyclerView.setAdapter(adapter_dairy);
                adapter_dairy.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("Fragment1", String.valueOf(databaseError.toException()));
            }
        });



        Log.d("test","check5");

        return view;

    }
}
