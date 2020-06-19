package com.d2w.dahada.data.activity_main.fragment_main.community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.d2w.dahada.R;

public class PhotoCommunity_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_community_);

        ImageButton btn_photo_post = (ImageButton)findViewById(R.id.btn_photo_post);
        btn_photo_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PhotoCommunity_Activity.this, PhotoPost_Activity.class);
                startActivity(myIntent);
            }
        });
    }
}