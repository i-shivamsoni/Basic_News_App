package com.example.basicnewsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public final class QueryUtil {

    private QueryUtil() {
    }

    public static List<NewsDetails> fetchNewsData(String stringUrl) {
        if (stringUrl == null) {
            return null;
        }

        URL url = createUrl(stringUrl);
        String jsonResponse = makeHttpRequest(url);
        return extractDataFromJson(jsonResponse);
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        if (stringUrl == null) {
            return null;
        }

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) {
        HttpURLConnection urlConnection;
        String jsonResponse = "";
        InputStream inputStream;

        if (url == null) {
            return null;
        }

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        Log.d(TAG, "makeHttpRequest: " + jsonResponse);
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) {
        InputStreamReader streamReader;
        StringBuilder result = new StringBuilder();
        BufferedReader bufferedReader;

        streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        bufferedReader = new BufferedReader(streamReader);

        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                result.append(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    private static List<NewsDetails> extractDataFromJson(String jsonResponse) {

        List<NewsDetails> newsList = new ArrayList<>();
        try {

            JSONObject json = new JSONObject(jsonResponse);
            JSONObject response = json.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {

                JSONObject currentNews = results.getJSONObject(i);
                String title = currentNews.getString("webTitle");
                String date = currentNews.getString("webPublicationDate");
                String url = currentNews.getString("webUrl");

                JSONArray authorTag = currentNews.getJSONArray("tags");
                String author = "";
                if (authorTag.length() != 0) {
                    JSONObject currentAuthorTag = authorTag.getJSONObject(0);
                    author = currentAuthorTag.getString("webTitle");
                }

                NewsDetails newsObject = new NewsDetails(title, author, date, url);
                newsList.add(newsObject);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
