package com.example.english_app.mainpage_fragments.lounge;

public class MesRcvModel {

    private final String userName, userMsg, uerMsgTime;
    public MesRcvModel(String userName, String userMsg, String uerMsgTime) {
        this.userName = userName;
        this.userMsg = userMsg;
        this.uerMsgTime = uerMsgTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public String getUerMsgTime() {
        return uerMsgTime;
    }
}
