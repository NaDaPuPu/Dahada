package com.d2w.dahada.data.activity_main.fragment_mypage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_mypage.adapter_notice.RecyclerItem;
import com.d2w.dahada.data.activity_main.fragment_mypage.adapter_notice.RecyclerNoticeAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class NoticeActivity extends AppCompatActivity {
    private static final String TAG = "NoticeActivity";

    RecyclerView mRecyclerView = null;
    RecyclerNoticeAdapter mAdapter = null;
    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();

    String gettedTitle;
    String gettedContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
        setContentView(R.layout.activity_notice);

        mRecyclerView = findViewById(R.id.recyclerView);

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new RecyclerNoticeAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        // 리사이클러뷰에 LinearLayoutManager 지정. (vertical)
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void addItem(Drawable icon, String title, String desc) {
        RecyclerItem item = new RecyclerItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);

        mList.add(item);
    }

    private void setup() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("mypage/notice/posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                gettedTitle = null;
                                gettedContent = null;
                                gettedTitle = document.get("title").toString();
                                gettedContent = document.get("content").toString();
                                addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icons8_calendar_64), gettedTitle, gettedContent);
                                Log.d(TAG,  gettedTitle + " => " + gettedContent);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }
}
