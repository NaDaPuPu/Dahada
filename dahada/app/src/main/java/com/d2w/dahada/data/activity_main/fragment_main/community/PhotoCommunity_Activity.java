package com.d2w.dahada.data.activity_main.fragment_main.community;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.diet.DietItem_nut;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class PhotoCommunity_Activity extends AppCompatActivity {

    private RecyclerView postList;
    private DatabaseReference PostRef;
    private ArrayList<Posts> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_community_);

        PostRef = FirebaseDatabase.getInstance().getReference().child("Posts");

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
        FirebaseRecyclerAdapter<Posts, PostsViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Posts, PostsViewHolder>
                        (
                                Posts.class,
                                R.layout.all_photo_post_layout,
                                PostsViewHolder.class,
                                PostRef

                        )
                {
                    @Override
                    protected void populateViewHolder(PostsViewHolder viewHolder, Posts model, int position) {
                        viewHolder.setNickname(model.getNickname());
                        viewHolder.setTime(model.getTime());
                        viewHolder.setDate(model.getDate());
                        viewHolder.setDescription(model.getDescription());
                        viewHolder.setPostImage(getApplicationContext(), model.getPostImage());



                    }
                };
        postList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class PostsViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

        }
        public void setNickname(String nickname)
        {
            TextView username = (TextView) mView.findViewById(R.id.post_profile_name);
            username.setText(nickname);
        }
        public void setTime(String time)
        {
            TextView PostTime = (TextView) mView.findViewById(R.id.post_time);
            PostTime.setText(time);
        }
        public void setDate(String date)
        {
            TextView PostDate = (TextView) mView.findViewById(R.id.post_date);
            PostDate.setText(date);
        }
        public void setDescription(String description)
        {
            TextView PostDescription = (TextView) mView.findViewById(R.id.post_description);
            PostDescription.setText(description);
        }
        public void setPostImage(Context ctx, String postImage)
        {
            ImageView PostImage = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(postImage).into(PostImage);

        }

    }
}