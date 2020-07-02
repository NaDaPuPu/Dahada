package com.d2w.dahada.data.activity_main.fragment_mypage;

public class Question {
    private String uid;
    private String date;
    private int type;
    private String title;
    private String content;
    private String answer;
    private String email;

    public Question() {

    }

    public Question(String uid, String date, int type, String title, String content, String answer, String email) {
        this.uid = uid;
        this.date = date;
        this.type = type;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public String getDate() {
        return date;
    }

    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getEmail() {
        return email;
    }

    public String getAnswer() {
        return answer;
    }
}
