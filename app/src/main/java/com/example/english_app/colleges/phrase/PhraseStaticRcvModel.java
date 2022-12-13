package com.example.english_app.colleges.phrase;

public class PhraseStaticRcvModel {

    private final int image;
    private final String text, text2;

    public PhraseStaticRcvModel(int image, String text, String text2) {
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

    public String getText2() {
        return text2;
    }
}
