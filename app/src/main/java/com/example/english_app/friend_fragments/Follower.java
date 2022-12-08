package com.example.english_app.friend_fragments;

public class Follower {

    //這裡和最後顯示在fragment的list有關
    //這裡是你的User裡面要顯示的有幾個物件的list，我們有userName和ID要顯示
    private String userName;
    private int userId;

    //在fragment java裡面的User function格式在這裡宣告有什麼變數
    public Follower(int userId, String  userName) {
        this.userId = userId;
        this.userName = userName;
    }

    //在fragment java 裡面接收到的user name 在這裡回傳值
    public String getUserName() {
        return userName;
    }

    //剛剛得到的值在這裡set現在得到的值
    public void setUserName(String userName) {
        this.userName = userName;
    }

    //id邏輯同上
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
