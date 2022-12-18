package com.example.english_app.colleges.reading;

public class ReadingDynamicRcvModel {
    private final String partText;
    private final String color;


    public ReadingDynamicRcvModel(String partText, String color) {
        this.partText = partText;
        this.color = color;
    }

    public String getUnitText() {
        return partText;
    }

    public String getColor() {
        return color;
    }
}
