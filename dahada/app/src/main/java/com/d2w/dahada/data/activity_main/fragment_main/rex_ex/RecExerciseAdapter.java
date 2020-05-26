package com.d2w.dahada.data.activity_main.fragment_main.rex_ex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.ItemAdapter;
import com.d2w.dahada.data.activity_main.fragment_main.recipe.RecipeItem;

import java.util.ArrayList;

public class RecExerciseAdapter extends RecyclerView.Adapter<RecExerciseAdapter.RecExViewHolder> {

    @NonNull
    @Override
    public RecExViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_rec_exercise_list_item, parent, false);
        final RecExViewHolder holder = new RecExViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecExViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecExViewHolder extends RecyclerView.ViewHolder {
        public RecExViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
