package com.example.english_app.colleges.vocab;

public class StaticRcvModel {

    private int image;
    private String text;

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
