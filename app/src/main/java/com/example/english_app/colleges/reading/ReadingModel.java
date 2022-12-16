package com.example.english_app.colleges.reading;

public class ReadingModel {
    private String newsTitle;
    private String newsDate;

    public ReadingModel(String newsTitle, String newsDate) {
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
