package com.d2w.dahada.data.activity_main.fragment_mypage.adapter_notice;

public class RecyclerItem {
    private String titleStr;
    private String dateStr;
    private String contentStr;

    public RecyclerItem(String date, String title, String content) {
        this.dateStr = date;
        this.titleStr = title;
        this.contentStr = content;
    }

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setContent(String content) {
        contentStr = content;
    }
    public void setDate(String date) {
        dateStr = date;
    }

    public String getTitle() {
        return this.titleStr;
    }
    public String getContent() {
        return this.contentStr;
    }
    public String getDate() {
        return this.dateStr;
    }
}
