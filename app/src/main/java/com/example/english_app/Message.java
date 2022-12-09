package com.example.english_app;

public class Message {
    private String userName;
    private String userMsg;
    private String userTime;


    //for sql get string data
    public Message() {

    }

    public Message(String userName, String userMsg, String userTime) {
        this.userName = userName;
        this.userMsg = userMsg;
        this.userTime = userTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }
}
