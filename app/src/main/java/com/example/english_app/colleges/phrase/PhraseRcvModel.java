package com.example.english_app.colleges.phrase;

public class PhraseRcvModel {
    private String vocText;
    private String chText;

    public PhraseRcvModel(String vocText, String chText) {
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
