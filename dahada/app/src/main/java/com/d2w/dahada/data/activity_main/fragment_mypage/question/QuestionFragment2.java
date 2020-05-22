package com.d2w.dahada.data.activity_main.fragment_mypage.question;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_mypage.adapter_question.RecyclerItem;
import com.d2w.dahada.data.activity_main.fragment_mypage.adapter_question.RecyclerQuestionAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class QuestionFragment2 extends Fragment {
    private static final String TAG = "QuestionFragment2";

    private FirebaseAuth mAuth;

    RecyclerQuestionAdapter adapter;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_question_2, container, false);
        mAuth = FirebaseAuth.getInstance();

        init();
        setup();
        return v;
    }

    private void init() {
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerQuestionAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(v.getContext(), 1));
    }

    private void setup() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("mypage/question/questions")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String gettedUid = document.get("uid").toString();
                                if (gettedUid.equals(mAuth.getUid())) {
                                    String gettedTitle;
                                    String gettedContent;
                                    String gettedAnswer;
                                    gettedTitle = document.get("title").toString();
                                    gettedContent = document.get("content").toString();
                                    if (document.get("answer") != null) {
                                        gettedAnswer = document.get("answer").toString();
                                    } else {
                                        gettedAnswer = null;
                                    }

                                    RecyclerItem item = new RecyclerItem(gettedTitle, gettedContent, gettedAnswer);
                                    adapter.addItem(item);
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
