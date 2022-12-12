package com.example.english_app.user_dorm.collections;

public class CollectionDynamicRcvModel {

    private final String unitText;
    private final String color;


    public CollectionDynamicRcvModel(String unitText, String color) {
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
