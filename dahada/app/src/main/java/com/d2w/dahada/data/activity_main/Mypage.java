package com.d2w.dahada.data.activity_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.data.mypage.MyPageList;
import com.d2w.dahada.data.mypage.MyPageListAdapter;
import com.d2w.dahada.data.mypage.OnMyPageListItemClickListener;
import com.d2w.dahada.R;
import com.d2w.dahada.data.login.LoginActivity;

public class Mypage extends Fragment {
    RecyclerView recyclerView;
    MyPageListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mypage, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new MyPageListAdapter();

        adapter.addItem(new MyPageList(getString(R.string.list_login)));
        adapter.addItem(new MyPageList("공지사항"));
        adapter.addItem(new MyPageList("문의 게시판"));
        adapter.addItem(new MyPageList("환경설정"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnMyPageListItemClickListener() {
            @Override
            public void onItemClick(MyPageListAdapter.ViewHolder holder, View view, int position) {
                MyPageList item = adapter.getItem(position);

                if (item.getName() == getString(R.string.list_login)) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(), "아이템 선택됨 : " + item.getName(), Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

}
