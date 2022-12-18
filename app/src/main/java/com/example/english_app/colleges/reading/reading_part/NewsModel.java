package com.example.english_app.colleges.reading.reading_part;

public class NewsModel {
    private String newsTitle;
    private String newsDate;

    public NewsModel(String newsTitle, String newsDate) {
        this.newsTitle = newsTitle;
        this.newsDate = newsDate;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsDate() {
        return newsDate;
    }
}
