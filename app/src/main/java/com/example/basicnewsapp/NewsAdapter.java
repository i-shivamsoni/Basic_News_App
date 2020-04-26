package com.example.basicnewsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsDetails> {

    List<NewsDetails> mNewsList;

    public NewsAdapter(@NonNull Context context, List<NewsDetails> newsList) {
        super(context, 0, newsList);
        mNewsList = newsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        NewsDetails currentNews = mNewsList.get(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView title = view.findViewById(R.id.news_title);
        title.setText(currentNews.getmTitle());

        String trimmedDate = currentNews.getmDate().substring(0, 10);

        TextView dateView = view.findViewById(R.id.date);
        dateView.setText(trimmedDate);

        TextView author = view.findViewById(R.id.author);
        author.setText(currentNews.getmAuthor());

        return view;
    }

}
