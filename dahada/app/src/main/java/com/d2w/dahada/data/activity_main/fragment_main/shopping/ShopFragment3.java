package com.d2w.dahada.data.activity_main.fragment_main.shopping;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.d2w.dahada.R;

public class ShopFragment3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_shop_3, container, false);
        Button btn_shop_pocket = (Button) view.findViewById(R.id.btn_shop_pocketsalad);
        btn_shop_pocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.pocketsalad.co.kr"));
                startActivity(myIntent);
            }
        });
        Button btn_shop_salady = (Button) view.findViewById(R.id.btn_shop_salady);
        btn_shop_salady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.saladykorea.com"));
                startActivity(myIntent);
            }
        });
        Button btn_shop_saladdays = (Button) view.findViewById(R.id.btn_shop_saladdays);
        btn_shop_saladdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.saladdays.co.kr"));
                startActivity(myIntent);
            }
        });
        return view;
    }
}
