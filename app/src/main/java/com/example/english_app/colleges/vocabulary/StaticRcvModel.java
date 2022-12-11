package com.example.english_app.colleges.vocabulary;

public class StaticRcvModel {

    private final int image;
    private final String text;

    public StaticRcvModel(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
