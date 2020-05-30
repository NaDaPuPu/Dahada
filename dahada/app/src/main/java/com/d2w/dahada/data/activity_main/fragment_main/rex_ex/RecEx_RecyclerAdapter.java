package com.d2w.dahada.data.activity_main.fragment_main.rex_ex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.List;

public class RecEx_RecyclerAdapter extends RecyclerView.Adapter<RecEx_ViewHolder> {
    private List<Movie> contents;

    public RecEx_RecyclerAdapter(@NonNull List<Movie> contents) { this.contents = contents; }

    @NonNull
    @Override
    public RecEx_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rec_exercise_list_item, parent, false);

        return new RecEx_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecEx_ViewHolder holder, final int position) {
        holder.youTubePlayerView.setEnableAutomaticInitialization(false);
        holder.youTubePlayerView.initialize(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(contents.get(position).videoId, 0);
            }
        }, false);
        holder.textView.setText(contents.get(position).videoname);
    }

    @Override
    public int getItemCount() {
        return (contents != null) ? contents.size() : 0;
    }
}
