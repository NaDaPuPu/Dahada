package com.d2w.dahada.data.activity_main.fragment_main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.community.PhotoCommunity_Activity;

public class CommunityActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        CardView cardView_freeCommunity = (CardView)findViewById(R.id.cardview_freeCommunity);
        cardView_freeCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CommunityActivity.this, FreeCommunityActivity.class);
                startActivity(myIntent);
            }
        });

        CardView cardView_photoCommunity = (CardView)findViewById(R.id.cardview_photoCommunity);
        cardView_photoCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CommunityActivity.this, PhotoCommunity_Activity.class);
                startActivity(myIntent);
            }
        });





    }

}