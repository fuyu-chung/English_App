package com.example.english_app.user_dorm.collections;

public class CollectionRcvModel {
    private String vocText;
    private String chText;

    public CollectionRcvModel(String vocText, String chText) {
        this.vocText = vocText;
        this.chText = chText;
    }

    public String getVocText() {
        return vocText;
    }

    public String getChText() {
        return chText;
    }
}
