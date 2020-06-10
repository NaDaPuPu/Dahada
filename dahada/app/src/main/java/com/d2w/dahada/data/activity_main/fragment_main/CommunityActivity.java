package com.d2w.dahada.data.activity_main.fragment_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.community.FirebaseID;
import com.d2w.dahada.data.activity_main.fragment_main.community.Post;
import com.d2w.dahada.data.activity_main.fragment_main.community.PostActivity;
import com.d2w.dahada.data.activity_main.fragment_main.community.PostAdapter;
import com.d2w.dahada.data.model.LoggedInUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommunityActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private RecyclerView mPostRecyclerview;

    private PostAdapter mAdapter;
    private List<Post> mDatas;
    private List<LoggedInUser> mlogingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        mPostRecyclerview = findViewById(R.id.main_recyclerview);

        findViewById(R.id.main_post_edit).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mlogingData = new ArrayList<>();
        mDatas = new ArrayList<>();
        mStore.collection(FirebaseID.post)
                //여기서부터 실시간으로 정보 불러오는 코드
                .orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (queryDocumentSnapshots != null) {
                            mDatas.clear();
                            for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                                Map<String, Object> shot = snap.getData();
                                String documentId = String.valueOf(shot.get(FirebaseID.documentId));
                                String user = String.valueOf(shot.get(FirebaseID.user));
                                String title = String.valueOf(shot.get(FirebaseID.title));
                                String contents = String.valueOf(shot.get(FirebaseID.contents));
                                Post data = new Post(documentId, title, contents);
                                mDatas.add(data);
                            }
                            mAdapter = new PostAdapter(mDatas);
                            mPostRecyclerview.setAdapter(mAdapter);
                        }
                    }
                });

    }

    @Override
    public void onClick(View v){
        startActivity(new Intent(this, PostActivity.class));
    }
}
