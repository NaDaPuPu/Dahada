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

public class DietAdapter_nut extends RecyclerView.Adapter<DietAdapter_nut.CustomViewHolder_nut> {

    private ArrayList<DietItem_nut> arrayList;
    private Context context;

    public DietAdapter_nut(ArrayList<DietItem_nut> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder_nut onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diet_list_item, parent, false);
        CustomViewHolder_nut holder = new CustomViewHolder_nut(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder_nut holder, int position) {

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getDietImage_nut())
                .into(holder.diet_iv_picture_nut);
        holder.diet_tv_id_nut.setText(arrayList.get(position).getDietName_nut());
        holder.diet_tv_cal_nut.setText(String.valueOf(arrayList.get(position).getDietKcal_nut()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHolder_nut extends RecyclerView.ViewHolder {

        ImageView diet_iv_picture_nut;
        TextView diet_tv_id_nut;
        TextView diet_tv_cal_nut;


        public CustomViewHolder_nut(@NonNull View itemView) {
            super(itemView);
            this.diet_iv_picture_nut = itemView.findViewById(R.id.diet_iv_picture);
            this.diet_tv_id_nut = itemView.findViewById(R.id.diet_tv_id);
            this.diet_tv_cal_nut = itemView.findViewById(R.id.diet_tv_cal);
        }
    }
}
