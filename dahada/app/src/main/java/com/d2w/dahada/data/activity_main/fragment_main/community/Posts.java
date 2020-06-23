package com.d2w.dahada.data.activity_main.fragment_main.community;

public class Posts {
    public String uid, time, date, post, postImage, description, nickname;

    public Posts()
    {

    }

    public Posts(String uid, String time, String date, String post, String postImage, String description, String nickname) {
        this.uid = uid;
        this.time = time;
        this.date = date;
        this.post = post;
        this.postImage = postImage;
        this.description = description;
        this.nickname = nickname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
