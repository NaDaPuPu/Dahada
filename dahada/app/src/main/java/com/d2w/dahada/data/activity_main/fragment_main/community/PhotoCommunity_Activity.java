package com.d2w.dahada.data.activity_main.fragment_main.community;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.d2w.dahada.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class PhotoCommunity_Activity extends AppCompatActivity {

    private RecyclerView postList;


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

        postList = (RecyclerView) findViewById(R.id.all_users_post_list);
        postList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        postList.setLayoutManager(linearLayoutManager);

        DisplayAllUserPost();

    }

    private void DisplayAllUserPost() {
       // FirebaseRecyclerAdapter<>
    }
}