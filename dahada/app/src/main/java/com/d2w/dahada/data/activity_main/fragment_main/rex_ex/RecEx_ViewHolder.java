package com.d2w.dahada.data.activity_main.fragment_main.rex_ex;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class RecEx_ViewHolder extends RecyclerView.ViewHolder {
    YouTubePlayerView youTubePlayerView;
    TextView textView;

    public RecEx_ViewHolder(@NonNull View itemView) {
        super(itemView);

        youTubePlayerView = itemView.findViewById(R.id.card_content_player_view);
        textView = itemView.findViewById(R.id.rec_ex_name);
    }
}
