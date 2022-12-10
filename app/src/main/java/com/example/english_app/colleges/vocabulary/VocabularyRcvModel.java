package com.example.english_app.colleges.vocabulary;

public class VocabularyRcvModel {
    private String vocText;
    private String chText;

    public VocabularyRcvModel(String vocText, String chText) {
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
