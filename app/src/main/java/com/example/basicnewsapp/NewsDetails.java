package com.example.basicnewsapp;

public class NewsDetails {
    private String mTitle;
    private String mDate;
    private String mAuthor;
    private String mUrl;

    public NewsDetails(String mTitle, String mAuthor, String mDate, String mUrl) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mDate = mDate;
        this.mUrl = mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmUrl() {
        return mUrl;
    }
}