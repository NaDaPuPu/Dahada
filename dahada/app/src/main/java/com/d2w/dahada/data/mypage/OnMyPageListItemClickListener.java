package com.d2w.dahada.data.mypage;

import android.view.View;

import com.d2w.dahada.data.mypage.MyPageListAdapter;

public interface OnMyPageListItemClickListener {
    public void onItemClick(MyPageListAdapter.ViewHolder holder, View view, int position);
}
