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

public class DietAdapter_dairy extends RecyclerView.Adapter<DietAdapter_dairy.CustomViewHolder_dairy> {

    private ArrayList<DietItem_dairy> arrayList;
    private Context context;

    public DietAdapter_dairy(ArrayList<DietItem_dairy> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder_dairy onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diet_list_item, parent, false);
        CustomViewHolder_dairy holder = new CustomViewHolder_dairy(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder_dairy holder, int position) {

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getDietImage_dairy())
                .into(holder.diet_iv_picture_dairy);
        holder.diet_tv_id_dairy.setText(arrayList.get(position).getDietName_dairy());
        holder.diet_tv_cal_dairy.setText(String.valueOf(arrayList.get(position).getDietKcal_dairy()));


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHolder_dairy extends RecyclerView.ViewHolder {

        ImageView diet_iv_picture_dairy;
        TextView diet_tv_id_dairy;
        TextView diet_tv_cal_dairy;

        public CustomViewHolder_dairy(@NonNull View itemView) {
            super(itemView);
            this.diet_iv_picture_dairy = itemView.findViewById(R.id.diet_iv_picture);
            this.diet_tv_id_dairy = itemView.findViewById(R.id.diet_tv_id);
            this.diet_tv_cal_dairy = itemView.findViewById(R.id.diet_tv_cal);
        }
    }
}
