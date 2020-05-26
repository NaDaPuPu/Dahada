package com.d2w.dahada.data.activity_main.fragment_main.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.ItemAdapter;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.RecipeItem;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    private ArrayList<ShopItem> shop_arrayList;
    private Context context;

    public ShopAdapter(ArrayList<ShopItem> arrayList, Context context) {
        this.shop_arrayList = shop_arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_shopping_list_item, parent, false);
        final ShopViewHolder holder = new ShopViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(shop_arrayList.get(position).getShopImage())
                .into(holder.shop_iv_picture);
        holder.shop_tv_name.setText(shop_arrayList.get(position).getShopName());
    }

    @Override
    public int getItemCount() {
        return shop_arrayList.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {

        CardView item;
        ImageView shop_iv_picture;
        TextView shop_tv_name;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            this.item = itemView.findViewById(R.id.shop_cardview);
            this.shop_iv_picture = itemView.findViewById(R.id.shop_iv_picture);
            this.shop_tv_name = itemView.findViewById(R.id.shop_tv_name);
        }
    }
}
