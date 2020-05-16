package com.d2w.dahada.data.activity_main.fragment_mypage.adapter_notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;

import java.util.ArrayList;

public class RecyclerNoticeAdapter extends RecyclerView.Adapter<RecyclerNoticeAdapter.ViewHolder> {
    private ArrayList<RecyclerItem> mData = null;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public RecyclerNoticeAdapter(ArrayList<RecyclerItem> list) {
        mData = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerNoticeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.notice_recycler_item, parent, false) ;
        RecyclerNoticeAdapter.ViewHolder vh = new RecyclerNoticeAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position ) {
        RecyclerItem item = mData.get(position);

        holder.icon.setImageDrawable(item.getIcon());
        holder.title.setText(item.getTitle());
        holder.desc.setText(item.getDesc());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;
        TextView desc;

        ViewHolder(final View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        RecyclerItem item = mData.get(pos);
                        Toast.makeText(itemView.getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
