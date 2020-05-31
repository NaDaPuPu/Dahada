package com.d2w.dahada.data.activity_main.fragment_main.rex_ex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.d2w.dahada.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.List;

public class RecEx_RecyclerAdapter extends RecyclerView.Adapter<RecEx_RecyclerAdapter.VideoViewHolder> {

    private List<Movie> movieList;

    public RecEx_RecyclerAdapter() {

    }

    public RecEx_RecyclerAdapter(List<Movie> movieList) {
        this.movieList = movieList;

    }
    @NonNull
    @Override
    public RecEx_RecyclerAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rec_exercise_list_item, parent, false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecEx_RecyclerAdapter.VideoViewHolder holder, int position) {
        holder.videoWeb.loadData(movieList.get(position).getVideoId(), "text/html" , "utf-8" );
        holder.videoname.setText(movieList.get(position).getVideoname());


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        WebView videoWeb;
        TextView videoname;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            videoWeb = (WebView) itemView.findViewById(R.id.videoWebView);
            this.videoname = itemView.findViewById(R.id.rec_ex_name);

            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient() {

            } );
        }
    }
}

