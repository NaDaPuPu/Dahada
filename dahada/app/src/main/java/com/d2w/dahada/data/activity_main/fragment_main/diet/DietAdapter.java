package com.d2w.dahada.data.activity_main.fragment_main.diet;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.DietActivity;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.RecipeDetails1;

import java.util.ArrayList;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.CustomViewHolderDiet> {
    private ArrayList<DietItem> arrayList;
    private Context context;
    private TextView textView_diet;




    public DietAdapter(ArrayList<DietItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolderDiet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diet_list_item, parent, false);
        final CustomViewHolderDiet holder = new CustomViewHolderDiet(view);


        holder.itemLinear_diet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DietActivity.class);
                intent.putExtra("position",holder.getAdapterPosition());
                Toast.makeText(context, "아이템 클릭됨"+String.valueOf(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();

                Editable edit = ((DietActivity) context).editText.getText();
                    int a,b;

                    if (edit.length() == 0) {
                        // 데이터1
                    } else if (edit.length() != 0 && !edit.toString().contains("+")) {
                        // 데이터 2
                    } else {
                        // 결과값과 데이터1, 데이터 2 연산한 값 출력
                    }
                }


        });
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
        LinearLayout itemLinear_diet;

        public CustomViewHolderDiet(@NonNull View itemView) {
            super(itemView);
            this.diet_iv_picture = itemView.findViewById(R.id.diet_iv_picture);
            this.diet_tv_id = itemView.findViewById(R.id.diet_tv_id);
            this.diet_tv_cal = itemView.findViewById(R.id.diet_tv_cal);
            this.itemLinear_diet = itemView.findViewById(R.id.itemLinear_diet);


        }
    }
}
