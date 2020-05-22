package com.d2w.dahada.data.activity_main.fragment_mypage.adapter_question;

public class RecyclerItem {
    private String titleStr;
    private String contentStr;
    private String answerStr;

    public RecyclerItem(String title, String content) {
        this.titleStr = title;
        this.contentStr = content;
    }

    public RecyclerItem(String title, String content, String answer) {
        this.titleStr = title;
        this.contentStr = content;
        this.answerStr = answer;
    }

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setContent(String content) {
        contentStr = content;
    }
    public void setAnswer(String answer) {
        answerStr = answer;
    }

    public String getTitle() {
        return this.titleStr;
    }
    public String getContent() {
        return this.contentStr;
    }
    public String getAnswer() {
        return this.answerStr;
    }
}
