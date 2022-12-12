package com.example.english_app.user_dorm.collections;

public class CollectionStaticRcvModel {

    private final int image;
    private final String text;

    public CollectionStaticRcvModel(int image, String text) {
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
