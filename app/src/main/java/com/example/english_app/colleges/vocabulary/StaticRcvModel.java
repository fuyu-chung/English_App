package com.example.english_app.colleges.vocabulary;

public class StaticRcvModel {

    private final int image;
    private final String text, text2;

    public StaticRcvModel(int image, String text, String text2) {
        this.image = image;
        this.text = text;
        this.text2 = text2;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public String getText2() { return text2; }
}
