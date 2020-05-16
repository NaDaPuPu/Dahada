package com.d2w.dahada.data.activity_main.fragment_mypage.adapter_notice;

import android.animation.ValueAnimator;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;

public class ViewHolderNotice extends RecyclerView.ViewHolder {
    private TextView date;
    private TextView title;
    private TextView content;

    private LinearLayout linearItem;

    private OnViewHolderItemClickListener onViewHolderItemClickListener;

    public ViewHolderNotice(@NonNull View itemView) {
        super(itemView);

        date = itemView.findViewById(R.id.date);
        title = itemView.findViewById(R.id.title);
        content = itemView.findViewById(R.id.content);
        linearItem = itemView.findViewById(R.id.linearItem);

        linearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewHolderItemClickListener.onViewHolderItemClick();
            }
        });
    }

    public void onBind(RecyclerItem item, int position, SparseBooleanArray selectedItems) {
        date.setText(item.getDate());
        title.setText(item.getTitle());
        content.setText(item.getContent());

        changeVisibility(selectedItems.get(position));
    }

    private void changeVisibility(final boolean isExpanded) {
        content.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }

}
