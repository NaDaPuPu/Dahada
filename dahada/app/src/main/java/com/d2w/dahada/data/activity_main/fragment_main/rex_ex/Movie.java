package com.d2w.dahada.data.activity_main.fragment_main.rex_ex;

import androidx.annotation.NonNull;

public class Movie {
    String videoId;
    String videoname;

    public Movie (@NonNull String videoId, String videoname) {
        this.videoId = videoId;
        this.videoname = videoname;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }
}
