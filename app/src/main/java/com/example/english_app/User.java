package com.example.english_app;

public class User {

//    private int resourceId;
    private String userName;
    private String userId;

    public User(String userName, String userId) {
//        this.resourceId = resourceId;
        this.userName = userName;
        this.userId = userId;
    }

//    public int getResourceId() {
//        return resourceId;
//    }
//
//    public void setResourceId(int resourceId) {
//        this.resourceId = resourceId;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
