package com.example.english_app.colleges.phrase;

public class PhraseStaticRcvModel {

    private final int image;
    private final String text;

    public PhraseStaticRcvModel(int image, String text) {
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
