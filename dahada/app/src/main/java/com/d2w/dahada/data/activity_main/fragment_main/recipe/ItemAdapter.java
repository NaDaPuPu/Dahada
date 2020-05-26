package com.d2w.dahada.data.activity_main.fragment_main.recipe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.d2w.dahada.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CustomViewHolder> {

    private ArrayList<RecipeItem> arrayList;
    private Context context;

    public ItemAdapter(ArrayList<RecipeItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recipe_list_item, parent, false);
        final CustomViewHolder holder = new CustomViewHolder(view);


        holder.item.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Test click"+String.valueOf(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getRecipeImage())
                .into(holder.iv_picture);
        holder.tv_id.setText(arrayList.get(position).getRecipeName());
        holder.tv_cal.setText(String.valueOf(arrayList.get(position).getRecipeKcal()));

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        CardView item;
        ImageView iv_picture;
        TextView tv_id;
        TextView tv_cal;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.item = itemView.findViewById(R.id.cardview_item);
            this.iv_picture = itemView.findViewById(R.id.iv_picture);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_cal = itemView.findViewById(R.id.tv_cal);

        }


    }
}
