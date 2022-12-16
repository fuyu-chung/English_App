package com.example.english_app.mainpage_fragments.Library;

public class LibraryModel {
    private String nTitle;
    private String nAuthor;

    public LibraryModel(String newsTitle, String newsDate) {
        this.nTitle = newsTitle;
        this.nAuthor = newsDate;
    }

    public String getNTitle() {
        return nTitle;
    }

    public String getNAuthor() {
        return nAuthor;
    }
}
