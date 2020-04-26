package com.example.basicnewsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsDetails>> {
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public List<NewsDetails> loadInBackground() {
        if(mUrl == null) {
            return null;
        }
        return QueryUtil.fetchNewsData(mUrl);
    }
}