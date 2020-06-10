package com.d2w.dahada.data.activity_main.fragment_main.diet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.d2w.dahada.R;

import java.util.ArrayList;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.CustomViewHolderDiet> {
    private ArrayList<DietItem> arrayList;
    private Context context;

    public DietAdapter(ArrayList<DietItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolderDiet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diet_list_item, parent, false);
        CustomViewHolderDiet holder = new CustomViewHolderDiet(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DietAdapter.CustomViewHolderDiet holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getDietImage())
                .into(holder.diet_iv_picture);
        holder.diet_tv_id.setText(arrayList.get(position).getDietName());
        holder.diet_tv_cal.setText(String.valueOf(arrayList.get(position).getDietKcal()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHolderDiet extends RecyclerView.ViewHolder {
        ImageView diet_iv_picture;
        TextView diet_tv_id;
        TextView diet_tv_cal;

        public CustomViewHolderDiet(@NonNull View itemView) {
            super(itemView);
            this.diet_iv_picture = itemView.findViewById(R.id.diet_iv_picture);
            this.diet_tv_id = itemView.findViewById(R.id.diet_tv_id);
            this.diet_tv_cal = itemView.findViewById(R.id.diet_tv_cal);
        }
    }
}
