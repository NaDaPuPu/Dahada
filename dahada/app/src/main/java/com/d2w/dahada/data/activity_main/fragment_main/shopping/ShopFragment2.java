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

public class ShopFragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_shop_2, container, false);
        Button btn_shop_ranking = (Button) view.findViewById(R.id.btn_shop_ranking_chiken);
        btn_shop_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://https://www.rankingdak.com/"));
                startActivity(myIntent);
            }
        });
        Button btn_shop_the_benefood = (Button) view.findViewById(R.id.btn_shop_the_benefood);
        btn_shop_the_benefood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.thebenefood.co.kr"));
                startActivity(myIntent);
            }
        });
        Button btn_shop_sleek = (Button) view.findViewById(R.id.btn_shop_sleek_market);
        btn_shop_sleek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sleekmarket.com"));
                startActivity(myIntent);
            }
        });
        return view;
    }
}
