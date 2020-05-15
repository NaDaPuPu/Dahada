package com.d2w.dahada.data.activity_main.fragment_main.recipe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    List<RecipeItem> itemListl;

    public ItemAdapter(List<RecipeItem> itemList) {

        this.itemListl=itemList;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recipe_list_item,parent,false);
        ViewHolder viewholder = new ViewHolder(view);


        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {

        holder.itemImage.setImageResource(itemListl.get(position).getRecipe_Image());
        holder.itemname.setText(itemListl.get(position).getRecipe_name());

    }

    @Override
    public int getItemCount() {
        return itemListl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemname;
        TextView itemgram;
        TextView itemkcal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.iv_profile);
            itemname = itemView.findViewById(R.id.txt_recipename);


        }
    }
}
