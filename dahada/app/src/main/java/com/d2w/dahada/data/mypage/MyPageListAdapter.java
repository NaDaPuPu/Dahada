package com.d2w.dahada.data.mypage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;

import java.util.ArrayList;

public class MyPageListAdapter extends RecyclerView.Adapter<MyPageListAdapter.ViewHolder> implements OnMyPageListItemClickListener {
    ArrayList<MyPageList> items = new ArrayList<MyPageList>();

    OnMyPageListItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.fragment_mypage_list_item, viewGroup, false);
        return new ViewHolder(itemView, this);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        MyPageList item = items.get(position);
        viewHolder.setItem(item);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public void addItem(MyPageList item) {
        items.add(item);
    }
    public void setItems(ArrayList<MyPageList> items) {
        this.items = items;
    }
    public MyPageList getItem(int position) {
        return items.get(position);
    }
    public void setItem(int position, MyPageList item) {
        items.set(position, item);
    }
    public void setOnItemClickListener(OnMyPageListItemClickListener listener) {
        this.listener = listener;
    }
    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView, final OnMyPageListItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textListView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }
        public void setItem(MyPageList item) {
            textView.setText(item.getName());
        }
    }
}
