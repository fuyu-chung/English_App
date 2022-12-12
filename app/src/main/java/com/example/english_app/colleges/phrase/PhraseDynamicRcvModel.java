package com.example.english_app.colleges.phrase;

public class PhraseDynamicRcvModel {

    private final String unitText;
    private final String color;


    public PhraseDynamicRcvModel(String unitText, String color) {
        this.unitText = unitText;
        this.color = color;
    }

    public String getUnitText() {
        return unitText;
    }

    public String getColor() {
        return color;
    }
}
