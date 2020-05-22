package com.d2w.dahada.data.activity_main.fragment_mypage.adapter_question;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;

public class ViewHolderQuestion extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView content;
    private TextView answer;

    private LinearLayout linearItem;

    private OnViewHolderItemClickListener onViewHolderItemClickListener;

    public ViewHolderQuestion(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title);
        content = itemView.findViewById(R.id.content);
        answer = itemView.findViewById(R.id.answer);
        linearItem = itemView.findViewById(R.id.linearItem);

        linearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewHolderItemClickListener.onViewHolderItemClick();
            }
        });
    }

    public void onBind(RecyclerItem item, int position, SparseBooleanArray selectedItems) {
        title.setText(item.getTitle());
        content.setText(item.getContent());
        answer.setText(item.getAnswer());

        changeVisibility(selectedItems.get(position));
    }

    private void changeVisibility(final boolean isExpanded) {
        content.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        if (answer.getText().toString() != "") {
            answer.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        } else {
            answer.setVisibility(View.GONE);
        }
    }

    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }
}
