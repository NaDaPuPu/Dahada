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

public class DietAdapter_meat extends RecyclerView.Adapter<DietAdapter_meat.CustomViewHoldeR_meat> {

    private ArrayList<DietItem_meat> arrayList;
    private Context context;

    public DietAdapter_meat(ArrayList<DietItem_meat> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHoldeR_meat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diet_list_item, parent, false);
        CustomViewHoldeR_meat holder = new CustomViewHoldeR_meat(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHoldeR_meat holder, int position) {

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getDietImage_meat())
                .into(holder.diet_iv_picture_meat);
        holder.diet_tv_id_meat.setText(arrayList.get(position).getDietName_meat());
        holder.diet_tv_cal_meat.setText(String.valueOf(arrayList.get(position).getDietKcal_meat()));


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHoldeR_meat extends RecyclerView.ViewHolder {

        ImageView diet_iv_picture_meat;
        TextView diet_tv_id_meat;
        TextView diet_tv_cal_meat;

        public CustomViewHoldeR_meat(@NonNull View itemView) {
            super(itemView);
            this.diet_iv_picture_meat = itemView.findViewById(R.id.diet_iv_picture);
            this.diet_tv_id_meat = itemView.findViewById(R.id.diet_tv_id);
            this.diet_tv_cal_meat = itemView.findViewById(R.id.diet_tv_cal);
        }
    }
}
