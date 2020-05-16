package com.d2w.dahada.data.activity_main.fragment_mypage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_mypage.adapter_notice.RecyclerItem;
import com.d2w.dahada.data.activity_main.fragment_mypage.adapter_notice.RecyclerNoticeAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NoticeActivity extends AppCompatActivity {
    private static final String TAG = "NoticeActivity";

    RecyclerNoticeAdapter adapter;

    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        init();
        setup();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // 리사이클러뷰에 LinearLayoutManager 지정. (vertical)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        adapter = new RecyclerNoticeAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 1));
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
                                String gettedDate;
                                String gettedTitle;
                                String gettedContent;
                                date = document.getDate("date");
                                gettedDate = format.format(date);
                                gettedTitle = document.get("title").toString();
                                gettedContent = document.get("content").toString();

                                RecyclerItem item = new RecyclerItem(gettedDate, gettedTitle, gettedContent);
                                adapter.addItem(item);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
